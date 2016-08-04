/**
 * 
 */
package com.howbuy.uaa.remote.service;

import com.howbuy.uaa.remote.dto.ChannelAdClickDto;
import com.howbuy.uaa.remote.dto.RPage;
import com.howbuy.uaa.remote.persistence.ChannelAdClick;

/**
 * @author qiankun.li
 *
 */
public interface ChannelAdClickService {
	
	/**
	 * 广告点击量 查询
	 * @param adClickDto
	 * @return
	 */
	public RPage<ChannelAdClick> queryTagClick(ChannelAdClickDto adClickDto);
	
}
