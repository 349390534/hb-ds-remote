/**
 * 
 */
package com.howbuy.uaa.remote.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.howbuy.uaa.remote.dao.CityIpDataDao;
import com.howbuy.uaa.remote.util.JsonParse;

/**
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("/cip")
public class CityIpDataAction extends RemoteBaseAction {
	
	@Autowired
	private CityIpDataDao dao;
	
	@RequestMapping(value = "/query.htm", method = RequestMethod.POST)
	public void queryCustLable(HttpServletRequest request,HttpServletResponse response,
			@RequestParam String beginTime,@RequestParam String endTime) {
		System.out.println("time:"+beginTime+","+endTime+"");
		List<Map<Object,Object>> list = dao.queryCityIpDataMap(beginTime,endTime);
		for(Map<Object,Object> m :list){
			String name =m.get("name").toString();
			if(StringUtils.isBlank(name))
				continue;
			name=name.replace("市", "");;
			m.put("name",name);
		}
		//String json = JsonParse.arrayToJsonStr(list);
		writeJson(response, list);
	}
	
	 
 }