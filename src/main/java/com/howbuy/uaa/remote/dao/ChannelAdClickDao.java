/**
 * 
 */
package com.howbuy.uaa.remote.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.howbuy.uaa.remote.dao.base.BaseDao;
import com.howbuy.uaa.remote.dto.ChannelAdClickDto;
import com.howbuy.uaa.remote.persistence.ChannelAdClick;

/**
 * @author qiankun.li
 *
 */
@Repository
public class ChannelAdClickDao extends BaseDao<ChannelAdClick, Long> {

	@Override
	protected String setNameSpace() {
		return "channel_ad_click_ns";
	}

	public List<ChannelAdClick> queryTagClick(ChannelAdClickDto adClickDto){
		List<ChannelAdClick> result = new ArrayList<ChannelAdClick>();
		String sqlId = "select_channel_ad_click_list";
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("beginDate",adClickDto.getBeginDate());
		paramMap.put("endDate", adClickDto.getEndDate());
		String tags = adClickDto.getTag();
		if(StringUtils.isNotBlank(tags)){
			String[] tagArray = tags.split(",");
			paramMap.put("tagArray", tagArray);
		}
		Integer pageNum = adClickDto.getPageIndex();
		Integer pageNumVar = (pageNum-1)* adClickDto.getTopnum();
		paramMap.put("pageNum", pageNumVar);
		paramMap.put("topNum", adClickDto.getTopnum());
		paramMap.put("proid", adClickDto.getProid());
		result= queryList(sqlId, paramMap);
		return result;
	}
	
}
