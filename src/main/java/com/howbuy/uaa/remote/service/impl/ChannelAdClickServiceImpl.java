/**
 * 
 */
package com.howbuy.uaa.remote.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.howbuy.uaa.remote.dao.ChannelAdClickDao;
import com.howbuy.uaa.remote.dto.ChannelAdClickDto;
import com.howbuy.uaa.remote.dto.RPage;
import com.howbuy.uaa.remote.persistence.ChannelAdClick;
import com.howbuy.uaa.remote.service.ChannelAdClickService;

/**
 * @author qiankun.li
 *
 */
@Service
@Transactional
public class ChannelAdClickServiceImpl implements ChannelAdClickService {

	@Autowired
	private ChannelAdClickDao adClickDao;
	
	/* 
	 * (non-Javadoc)
	 * @see com.howbuy.uaa.remote.service.ChannelAdClickService#queryTagClick(com.howbuy.uaa.remote.dto.ChannelAdClickDto)
	 */
	@Override
	public RPage<ChannelAdClick> queryTagClick(ChannelAdClickDto adClickDto) {
		RPage<ChannelAdClick> page = new RPage<ChannelAdClick>();
		List<ChannelAdClick>  result=adClickDao.queryTagClick(adClickDto);
		page.setResponse(result);
		long  count = 0;
		page.setCount(count);
		return page;
	}

}
