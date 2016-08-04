/**
 * 
 */
package com.howbuy.uaa.remote.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import scala.actors.threadpool.Arrays;

import com.howbuy.dao.CustLableDao;
import com.howbuy.hbasetokafka.HbaseToKafka;
import com.howbuy.uaa.remote.service.CustLabelService;
import com.howbuy.uaa.remote.util.IpUtil;
import com.howbuy.util.HbasePage;

/**
 * @author qiankun.li 查询hbase用户标签数据
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("/custlable")
public class CustLableAction extends RemoteBaseAction {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustLableAction.class);

	@Autowired
	private CustLabelService custLabelService;

	private final String  TABLE_NAME="DIS_CUST_LABLE_INFO";
	private final String  TABLE_COLUMN_FAMILY="CUST_LABLE_CF";
	
	private HbasePage hbasePage=new HbasePage();
	
	private HbaseToKafka hbaseToKafka =new HbaseToKafka();
	
	private CustLableDao lableDao = new CustLableDao();
	
	/**查询客户标签数据
	 * @param request
	 * @param response
	 * @param pageIndex 页码
	 * @param pageNum 
	 * @param isall
	 */
	@Deprecated
	@RequestMapping(value = "/query.htm", method = RequestMethod.GET)
	public void queryCustLable(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam Integer pageIndex,
			@RequestParam(required = false)  Integer pageNum,
			@RequestParam(required = false)  Integer isall) {
		LOGGER.info("IP :"+new IpUtil().getIpAddr(request));
		LOGGER.info("request params:"+pageIndex +","+pageNum+","+isall);
		Map<Object,Object> fieldCodeMap = initFiledCodeMap();
		HbasePage.TBData data = new HbasePage.TBData();
		try {
			data = hbasePage.getDataMap(TABLE_NAME, pageIndex, pageNum, TABLE_COLUMN_FAMILY);
		} catch (IOException e) {
			LOGGER.error("search hbase data error",e);
		}
		List<Map<String, String>> result=data.getResultList();
		List<Map<String, String>> newResult= convertMapToAs(result, fieldCodeMap);
		
		HbasePage.TBData responseData  = new HbasePage.TBData();
		responseData.setCurrentPage(data.getCurrentPage());
		responseData.setPageSize(data.getPageSize());
		responseData.setTotalCount(data.getTotalCount());
		responseData.setTotalPage(data.getTotalPage());
		responseData.setResultList(newResult);
		writeJson(response, responseData);
	}
	
	/**
	 * 通知更新接口
	 * @param request
	 * @param response
	 * @param pageIndex
	 * @param pageNum
	 * @param isall
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/notify.htm", method = RequestMethod.GET)
	public void notify(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(required = false) final  String custno,
			@RequestParam(required = false) final  Integer isall) {
		LOGGER.info("IP :"+new IpUtil().getIpAddr(request));
		LOGGER.info("request params:"+custno +","+isall);
		Map<String,String> responseMap = new HashMap<String,String>();
		try {
			lableDao.initColData();
			if(StringUtils.isBlank(custno)){
				new Thread(){
					boolean isUpdateAll =false;//默认推送增量数据
					@Override
					public void run() {
						if(null != isall && isall.intValue()==1){
							isUpdateAll = true;
						}
						try {
							hbaseToKafka.hbaseToKafka(isUpdateAll);
						} catch (IOException e) {
							LOGGER.error("notify error ",e);
						} 
					}
					
				}.start();
			}else{
				new Thread(){
					@Override
					public void run() {
						List<String> custList = (List<String>)Arrays.asList(custno.split(","));
						hbaseToKafka.hbaseToKafka(custList);
					}
				}.start();
			}
			responseMap.put("status", "1");
		} catch (Exception e) {
			LOGGER.error("notify error ",e);
			responseMap.put("status", "0");
		}
		writeJson(response, responseMap);
	}
	
	
	private List<Map<String, String>> convertMapToAs(List<Map<String, String>> result,Map<Object,Object> fieldCodeMap){
		List<Map<String, String>> cmapList = new ArrayList<Map<String,String>>(result.size());
		for(Map<String, String> m:result){
			Set<Entry<String, String>> set=m.entrySet();
			Map<String,String> newm = new HashMap<String, String>();
			for(Entry<String, String> en:set){
				String col = en.getKey();
				String value=en.getValue();
				Object colAs = fieldCodeMap.get(col);
				if(null!=colAs){
					newm.put(colAs.toString(), value);
				}
			}
			cmapList.add(newm);
		}
		return cmapList;
	}
	
	
	@SuppressWarnings("unchecked")
	private  HashMap<Object,Object> initFiledCodeMap(){
		Map<Object,Object> fieldCodeMap =custLabelService.getMapping();
		Set<Entry<Object, Object>> set = fieldCodeMap.entrySet();
		HashMap<Object, Object> allFiledsCode=new HashMap<Object, Object>();
		for(Entry<Object, Object> en:set){
			Object key =en.getKey();
			System.out.println(key);
			HashMap<Object, Object> v =(HashMap<Object, Object>) en.getValue();
			allFiledsCode.put(v.get("tagName"),v.get("tagCode"));
			
		}
		return allFiledsCode;
	}
 }