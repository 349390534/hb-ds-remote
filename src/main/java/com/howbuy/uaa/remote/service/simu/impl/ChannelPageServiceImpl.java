/**
 * 
 */
package com.howbuy.uaa.remote.service.simu.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.howbuy.uaa.remote.dao.simu.ChannelPageDao;
import com.howbuy.uaa.remote.dto.RPage;
import com.howbuy.uaa.remote.dto.simu.ChannelPageDto;
import com.howbuy.uaa.remote.persistence.ChannelPage;
import com.howbuy.uaa.remote.service.simu.ChannelPageService;

/**
 * @author qiankun.li
 *
 */
@Service
@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
public class ChannelPageServiceImpl implements ChannelPageService {

	@Autowired
	private ChannelPageDao channelPageDao;
	
	@Override
	public List<ChannelPage> queryChannelPageList(ChannelPageDto channelPageDto) {
		return channelPageDao.queryChannelPageList(channelPageDto);
	}

	
	@Override
	public RPage<ChannelPage> queryChannelPageListZx(
			ChannelPageDto channelPageDto) {
		List<ChannelPage> response = channelPageDao.queryChannelPageListZx(channelPageDto);
		RPage<ChannelPage> page = new RPage<ChannelPage>();
		page.setResponse(response);
		Long count = channelPageDao.queryChannelPageListZxCount(channelPageDto);
		page.setCount(count);
		return page;
	}
	
	@Override
	public ChannelPage selectChannelPageListSum(ChannelPageDto channelPageDto) {
		return channelPageDao.selectFilteredChannelPageSum(channelPageDto);
	}

	@Override
	public ChannelPage getVisitCount(ChannelPageDto channelPageDto) {
		return channelPageDao.selectVisitCount(channelPageDto);
	}


	public ChannelPageDao getChannelPageDao() {
		return channelPageDao;
	}

	public void setChannelPageDao(ChannelPageDao channelPageDao) {
		this.channelPageDao = channelPageDao;
	}


	

	
}
