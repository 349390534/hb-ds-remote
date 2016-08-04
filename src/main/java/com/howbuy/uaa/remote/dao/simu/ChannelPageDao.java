/**
 * 
 */
package com.howbuy.uaa.remote.dao.simu;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.howbuy.uaa.remote.dao.base.BaseDao;
import com.howbuy.uaa.remote.dto.simu.ChannelPageDto;
import com.howbuy.uaa.remote.persistence.ChannelPage;
import com.howbuy.uaa.remote.util.DateUtils;

/**
 * @author qiankun.li
 *
 */
@Repository
public class ChannelPageDao extends BaseDao<ChannelPage, Long> {

	@Override
	protected String setNameSpace() {
		return "channel_page_puv";
	}
	
	public List<ChannelPage> queryChannelPageList(ChannelPageDto channelPageDto){
		String sqlId = "select_channel_page_list_sm";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageType", channelPageDto.getPageType());
		map.put("beginDate", channelPageDto.getBeginDate());
		map.put("endDate", channelPageDto.getEndDate());
		map.put("proid", channelPageDto.getProid());
		map.put("topNum", channelPageDto.getTopNum());
		return queryList(sqlId, map);
	}

	/**
	 * 是否超过了当天
	 * @param endDt
	 * @return
	 */
	private boolean isMoreThanToday(String endDt){
		if(StringUtils.isBlank(endDt))return false;
		Date endD = DateUtils.parseDate(endDt, DateUtils.FORMAT_D_YYYYMMDD);
		Date dtToday = new Date();
		return endD.compareTo(dtToday)<0?false:true;
	}
	
	private void addTodayDt(String endDt,Map<String, Object> map){
		if(isMoreThanToday(endDt))
			map.put("todayDt", DateUtils.getFormatedDate(new Date(), DateUtils.FORMAT_D_YYYYMMDD));
	}
	public List<ChannelPage> queryChannelPageListZx(ChannelPageDto channelPageDto){
		String sqlId = "select_zx_page_list";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("proid", channelPageDto.getProid());
		map.put("pageid_all", channelPageDto.getPageId());
		String ids = channelPageDto.getIds();
		if(StringUtils.isNotBlank(ids)){
			map.put("ids", ids.split(","));
		}
		String newsType = channelPageDto.getNewsType();
		if(StringUtils.isNotBlank(newsType)){
			map.put("newsType", newsType.split(","));
		}
		String subtype = channelPageDto.getSubtype();
		if(StringUtils.isNotBlank(subtype)){
			map.put("subtype", subtype.split(","));
		}
		map.put("author", channelPageDto.getAuthor());
		map.put("topNum", channelPageDto.getTopNum());
		map.put("beginDate", channelPageDto.getBeginDate());
		String endDt = channelPageDto.getEndDate();
		map.put("endDate",endDt) ;
		Integer pageNum = channelPageDto.getPageNum();
		Integer pageNumVar = (pageNum-1)* channelPageDto.getTopNum();
		map.put("pageNum", pageNumVar);
		addTodayDt(endDt, map);
		return queryList(sqlId, map);
	}
	
	public ChannelPage selectFilteredChannelPageSum(ChannelPageDto channelPageDto){
		String sqlId = "select_zx_page_list_sum";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("proid", channelPageDto.getProid());
		map.put("pageid_all", channelPageDto.getPageId());
		String ids = channelPageDto.getIds();
		if(StringUtils.isNotBlank(ids)){
			map.put("ids", ids.split(","));
		}
		String newsType = channelPageDto.getNewsType();
		if(StringUtils.isNotBlank(newsType)){
			map.put("newsType", newsType.split(","));
		}
		String subtype = channelPageDto.getSubtype();
		if(StringUtils.isNotBlank(subtype)){
			map.put("subtype", subtype.split(","));
		}
		map.put("author", channelPageDto.getAuthor());
		map.put("topNum", channelPageDto.getTopNum());
		map.put("beginDate", channelPageDto.getBeginDate());
		String endDt = channelPageDto.getEndDate();
		map.put("endDate",endDt );
		addTodayDt(endDt, map);
		return selectOne(sqlId, map);
	}
	
	public Long queryChannelPageListZxCount(ChannelPageDto channelPageDto){
		String sqlId = "count_select_zx_page_list";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("proid", channelPageDto.getProid());
		map.put("pageid_all", channelPageDto.getPageId());
		String ids = channelPageDto.getIds();
		if(StringUtils.isNotBlank(ids)){
			map.put("ids", ids.split(","));
		}
		String newsType = channelPageDto.getNewsType();
		if(StringUtils.isNotBlank(newsType) ){
			map.put("newsType", newsType.split(","));
		}
		String subtype = channelPageDto.getSubtype();
		if(StringUtils.isNotBlank(subtype)){
			map.put("subtype", subtype.split(","));
		}
		map.put("author", channelPageDto.getAuthor());
		map.put("topNum", channelPageDto.getTopNum());
		map.put("beginDate", channelPageDto.getBeginDate());
		String endDt = channelPageDto.getEndDate();
		map.put("endDate",endDt);
		addTodayDt(endDt, map);
		return selectOne(sqlId, map);
	}

	
	public ChannelPage selectVisitCount(ChannelPageDto channelPageDto){
		String sqlId = "select_count_pvuv_all";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("proid", channelPageDto.getProid());
		map.put("pageid", channelPageDto.getPageId());
		map.put("beginDate", channelPageDto.getBeginDate());
		String endDt = channelPageDto.getEndDate();
		map.put("endDate", endDt);
		addTodayDt(endDt, map);
		return selectOne(sqlId, map);
	}
	
}
