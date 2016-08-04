/**
 * 
 */
package com.howbuy.uaa.remote.action.simu;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

import com.howbuy.uaa.remote.action.RemoteBaseAction;
import com.howbuy.uaa.remote.common.ProidType;
import com.howbuy.uaa.remote.common.QueryPageType;
import com.howbuy.uaa.remote.dto.simu.ChannelPageDto;
import com.howbuy.uaa.remote.dto.simu.ResponseData;
import com.howbuy.uaa.remote.persistence.ChannelPage;
import com.howbuy.uaa.remote.service.simu.ChannelPageService;
import com.howbuy.uaa.remote.util.DateUtils;

/**
 * @author qiankun.li
 * 私募流量数据查询
 */
@Controller
@Scope(value="prototype")
@RequestMapping("/simu")
public class SimuPageAction extends RemoteBaseAction{

	private static final Logger LOGGER = LoggerFactory.getLogger(SimuPageAction.class);
	
	@Autowired
	private ChannelPageService channelPageService;
	
	
	/**
	 * 查询私募浏览量拍前几名的数据
	 * @param request
	 * @param response
	 * @param beginDate
	 * @param endDate
	 * @param days
	 * @param topnum
	 */
	@RequestMapping(value="/querytop.htm",method=RequestMethod.GET)
	public void querySimuTopClick(HttpServletRequest request,HttpServletResponse response,
		@RequestParam String pageType,
		@RequestParam(required=false) String beginDate,@RequestParam(required=false) String endDate, 
		@RequestParam(required=false) Integer days,
		@RequestParam(required=false) Integer topnum){
		ResponseData<ChannelPage> responseData = new ResponseData<ChannelPage>();
		if(StringUtils.isBlank(pageType)){
			responseData.setStatus("1");
			responseData.setDesc("缺少参数pageType");
			writeJson(response, responseData);
			return;
		}
		String begin = null;
		String end = null;
		if(null != days){
			// 计算截止到昨天的数据
			Calendar now = Calendar.getInstance();
			now.add(Calendar.DATE, -1);//跳到昨天
			end = DateUtils.getFormatedDate(now.getTime(), DateUtils.FORMAT_D_YYYYMMDD);
			Date beginDateVar = DateUtils.getDate(end, -1*days.intValue());
			begin = DateUtils.getFormatedDate(beginDateVar,DateUtils.FORMAT_D_YYYYMMDD);
		}else{
			if(StringUtils.isBlank(beginDate) && StringUtils.isBlank(endDate)){
				// error
				responseData.setStatus("1");
				responseData.setDesc("缺少参数");
				writeJson(response, responseData);
				return;
			}else if(StringUtils.isNotBlank(beginDate) && StringUtils.isNotBlank(endDate) ){
				begin = beginDate;
				end = endDate;
			}else{
				responseData.setStatus("1");
				responseData.setDesc("缺少参数");
				writeJson(response, responseData);
				return;
			}
		}
		if(null==topnum){
			topnum = 10;
		}
		ChannelPageDto channelPageDto = new ChannelPageDto();
		QueryPageType type = QueryPageType.findByIndex(pageType);
		if(null == type){
			responseData.setStatus("1");
			responseData.setDesc("非法的参数pageType");
			writeJson(response, responseData);
			return;
		}
		channelPageDto.setPageType(pageType);
		channelPageDto.setProid(ProidType.PROID_SIMU.getIndex());
		channelPageDto.setTopNum(topnum);
		channelPageDto.setBeginDate(begin);
		channelPageDto.setEndDate(end);
		List<ChannelPage> result;
		try {
			result = channelPageService.queryChannelPageList(channelPageDto);
			responseData.setStatus("0");
			responseData.setResponse(result);
		} catch (Exception e) {
			LOGGER.error("queryChannelPageList is error",e);
			responseData.setStatus("1");
		}
		writeJson(response, responseData);
	}
	
}
