/**
 * 
 */
package com.howbuy.uaa.remote.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.howbuy.uaa.remote.common.ProidType;
import com.howbuy.uaa.remote.dto.ChannelAdClickDto;
import com.howbuy.uaa.remote.dto.RPage;
import com.howbuy.uaa.remote.dto.simu.ResponseData;
import com.howbuy.uaa.remote.persistence.ChannelAdClick;
import com.howbuy.uaa.remote.service.ChannelAdClickService;

/**
 * @author qiankun.li
 * 广告点击量数据查询
 */
@Controller
@Scope(value="prototype")
@RequestMapping("/ad")
public class ChannelAdAction extends RemoteBaseAction{

	private static final Logger LOGGER = LoggerFactory.getLogger(ChannelAdAction.class);
	
	@Autowired
	private ChannelAdClickService adClickService;
	
	
	
	/**
	 * 查询私募浏览量拍前几名的数据
	 * @param request
	 * @param response
	 * @param beginDate
	 * @param endDate
	 * @param days
	 * @param topnum
	 */
	@RequestMapping(value="/queryclick.htm",method=RequestMethod.GET)
	public void queryAdClick(HttpServletRequest request,HttpServletResponse response,
		@RequestParam(required=false) String beginDate,@RequestParam(required=false) String endDate, 
		@RequestParam(required=false) Integer pageIndex,
		@RequestParam(required=false) String tag,
		@RequestParam(required=false) Integer topnum){
		ResponseData<ChannelAdClick> responseData = new ResponseData<ChannelAdClick>();
		ChannelAdClickDto adClickDto = new ChannelAdClickDto();
		adClickDto.setBeginDate(beginDate);
		adClickDto.setEndDate(endDate);
		Integer pageIndexVar = pageIndex==null?1:pageIndex;
		adClickDto.setPageIndex(pageIndexVar);
		adClickDto.setTag(tag);
		Integer topnumVar = topnum==null?10:topnum;
		adClickDto.setTopnum(topnumVar);
		adClickDto.setProid(ProidType.PROID_AD.getIndex());
		
		
		try {
			RPage<ChannelAdClick> page = adClickService.queryTagClick(adClickDto);
			List<ChannelAdClick> result = page.getResponse();
			responseData.setStatus("0");
			responseData.setResponse(result);
			responseData.setCount(page.getCount());
		} catch (Exception e) {
			LOGGER.error("queryAdClick is error ",e);
			responseData.setStatus("1");
			responseData.setDesc(e.getMessage());
		}
		writeJson(response, responseData);
	}
	
}
