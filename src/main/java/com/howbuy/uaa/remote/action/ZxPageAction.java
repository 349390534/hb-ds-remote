/**
 * 
 */
package com.howbuy.uaa.remote.action;

import java.util.ArrayList;
import java.util.Date;
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
import com.howbuy.uaa.remote.dto.RPage;
import com.howbuy.uaa.remote.dto.simu.ChannelPageDto;
import com.howbuy.uaa.remote.dto.simu.ResponseData;
import com.howbuy.uaa.remote.persistence.ChannelPage;
import com.howbuy.uaa.remote.service.simu.ChannelPageService;
import com.howbuy.uaa.remote.util.DateUtils;
import com.howbuy.uaa.remote.util.IpUtil;

/**
 * @author qiankun.li 资讯流量数据查询
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("/zx")
public class ZxPageAction extends RemoteBaseAction {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ZxPageAction.class);

	@Autowired
	private ChannelPageService channelPageService;

	/**
	 * 查询资讯流量排名的数据
	 * 
	 * @param request
	 * @param response
	 * @param beginDate
	 * @param endDate
	 * @param days
	 * @param topnum
	 */
	@RequestMapping(value = "/querytop.htm", method = RequestMethod.GET)
	public void queryZXTopClick(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String beginDate,
			@RequestParam(required = false) String endDate, 
			@RequestParam Integer pageIndex,
			@RequestParam(required = false) String ids,
			@RequestParam(required = false) String newstype,
			@RequestParam(required = false) String subtype,
			@RequestParam(required = false) String author,
			@RequestParam(required = false) Integer topnum) {
		LOGGER.info("IP :"+new IpUtil().getIpAddr(request));
		LOGGER.info("request params:"+beginDate +","+endDate+","+pageIndex+","
				+ids+","+newstype+","+subtype+","+author+","+topnum);
		
		ResponseData<ChannelPage> responseData = new ResponseData<ChannelPage>();
		ChannelPageDto channelPageDto = new ChannelPageDto();
		Date beginDateTime = DateUtils.parseDate(beginDate,	DateUtils.FORMAT_D_YYYYMMDD);
		if (null == beginDateTime) {
			LOGGER.error("beginDate is error ");
			responseData.setDesc("beginDate is error ");
			responseData.setStatus("1");
			return;
		}
		Date endPast = DateUtils.addDays(new Date(), 1);
		if(null == endDate){
			endDate = DateUtils.getFormatedDate(endPast);
		}
		Date fromMonth = DateUtils.addMonths(endPast, -2);// 最大支持2个月数据查询
		if (beginDateTime.compareTo(fromMonth) < 0) {// 超过2个月的数据
			beginDate = DateUtils.getFormatedDate(fromMonth);
		}
		channelPageDto.setBeginDate(beginDate);
		channelPageDto.setEndDate(endDate);
		channelPageDto.setPageNum(pageIndex);
		channelPageDto.setProid(ProidType.PROID_ZIXUN.getIndex());
		channelPageDto.setIds(ids);
		channelPageDto.setSubtype(subtype);
		channelPageDto.setNewsType(newstype);
		channelPageDto.setAuthor(author);
		int top = topnum == null ? 10 : topnum.intValue();// 默认取前十条
		channelPageDto.setTopNum(top);
		String pageidAll = "-1";
		channelPageDto.setPageId(pageidAll);
		try {
			RPage<ChannelPage> resultPage = channelPageService.queryChannelPageListZx(channelPageDto);
			List<ChannelPage> result = resultPage.getResponse();
			responseData.setStatus("0");
			responseData.setResponse(result);
			responseData.setCount(resultPage.getCount());
		} catch (Exception e) {
			LOGGER.error("queryChannelPageListZx is error ", e);
			responseData.setStatus("1");
			responseData.setDesc(e.getMessage());
		}
		writeJson(response, responseData);
	}
	
	/**
	 * 按条件过滤明细数据，并汇总
	 * @param request
	 * @param response
	 * @param beginDate
	 * @param endDate
	 * @param pageIndex
	 * @param ids
	 * @param newstype
	 * @param subtype
	 * @param author
	 * @param topnum
	 */
	@RequestMapping(value = "/selectFilteredChannelPageSum.htm", method = RequestMethod.GET)
	public void selectChannelPageListSum(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String beginDate,
			@RequestParam(required = false) String endDate, 
			@RequestParam(required = false) String ids,
			@RequestParam(required = false) String newstype,
			@RequestParam(required = false) String subtype,
			@RequestParam(required = false) String author) {
		
		LOGGER.info("IP :"+new IpUtil().getIpAddr(request));
		
		ResponseData<ChannelPage> responseData = new ResponseData<ChannelPage>();
		ChannelPageDto channelPageDto = new ChannelPageDto();
		Date beginDateTime = DateUtils.parseDate(beginDate,	DateUtils.FORMAT_D_YYYYMMDD);
		if (null == beginDateTime) {
			LOGGER.error("beginDate is error ");
			responseData.setDesc("beginDate is error ");
			responseData.setStatus("1");
			return;
		}
		Date endPast = DateUtils.addDays(new Date(), 1);
		if(null == endDate){
			endDate = DateUtils.getFormatedDate(endPast);
		}
		Date fromMonth = DateUtils.addMonths(endPast, -2);// 最大支持2个月数据查询
		if (beginDateTime.compareTo(fromMonth) < 0) {// 超过2个月的数据
			beginDate = DateUtils.getFormatedDate(fromMonth);
		}
		channelPageDto.setBeginDate(beginDate);
		channelPageDto.setEndDate(endDate);
		channelPageDto.setProid(ProidType.PROID_ZIXUN.getIndex());
		channelPageDto.setIds(ids);
		channelPageDto.setSubtype(subtype);
		channelPageDto.setNewsType(newstype);
		channelPageDto.setAuthor(author);
		String pageidAll = "-1";
		channelPageDto.setPageId(pageidAll);
		try {
			ChannelPage resultPage = channelPageService.selectChannelPageListSum(channelPageDto);
			List<ChannelPage> result = new ArrayList<ChannelPage>();
			result.add(resultPage);
			responseData.setStatus("0");
			responseData.setResponse(result);
		} catch (Exception e) {
			LOGGER.error("queryChannelPageListZx is error ", e);
			responseData.setStatus("1");
			responseData.setDesc(e.getMessage());
		}
		writeJson(response, responseData);
	}

	@RequestMapping(value = "/selectZxVisitAll.htm", method = RequestMethod.GET)
	public void selectZxVistiAll(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(required = false) String beginDate,
			@RequestParam(required = false) String endDate) {
		ResponseData<ChannelPage> responseData = new ResponseData<ChannelPage>();
		ChannelPageDto channelPageDto = new ChannelPageDto();
		Date beginDateTime = DateUtils.parseDate(beginDate,DateUtils.FORMAT_D_YYYYMMDD);
		if (null == beginDateTime) {
			LOGGER.error("beginDate is error of selectZxVisitAll ");
			responseData.setDesc("beginDate is error ");
			responseData.setStatus("1");
			return;
		}
		Date endPast = DateUtils.addDays(new Date(), 1);
		Date fromMonth = DateUtils.addMonths(endPast, -2);// 最大支持2个月数据查询
		if (beginDateTime.compareTo(fromMonth) < 0) {// 超过2个月的数据
			beginDate = DateUtils.getFormatedDate(fromMonth);
		}
		channelPageDto.setBeginDate(beginDate);
		channelPageDto.setEndDate(endDate);
		channelPageDto.setProid(ProidType.PROID_ZIXUN.getIndex());
		channelPageDto.setPageId("-1");

		try {
			ChannelPage channelPage = channelPageService.getVisitCount(channelPageDto);
			List<ChannelPage> result = new ArrayList<ChannelPage>();
			if(null == channelPage)
				channelPage = new ChannelPage();
			result.add(channelPage);
			responseData.setStatus("0");
			responseData.setResponse(result);
		} catch (Exception e) {
			LOGGER.error("selectZxVisitAll is error ", e);
			responseData.setStatus("1");
			responseData.setDesc(e.getMessage());
		}

		writeJson(response, responseData);

	}
}