/**
 * 
 */
package com.howbuy.uaa.remote.service.simu;

import java.util.List;

import com.howbuy.uaa.remote.dto.RPage;
import com.howbuy.uaa.remote.dto.simu.ChannelPageDto;
import com.howbuy.uaa.remote.persistence.ChannelPage;

/**
 * @author qiankun.li
 * 私募 数据接口
 */
public interface ChannelPageService {
	
	/**查询数据流量最近N天的前M条数据
	 * @param channelPageDto
	 * @return
	 */
	public List<ChannelPage> queryChannelPageList(ChannelPageDto channelPageDto);
	
	/**查询资讯数据流量最近N天的前M条数据
	 * @param channelPageDto
	 * @return
	 */
	public RPage<ChannelPage> queryChannelPageListZx(ChannelPageDto channelPageDto);
	
	/**
	 * 明细过滤，并汇总
	 * @param channelPageDto
	 * @return
	 */
	public ChannelPage selectChannelPageListSum(ChannelPageDto channelPageDto);
	
	/**获取一段时间内总访问量PV、UV
	 * @return
	 */
	public ChannelPage getVisitCount(ChannelPageDto channelPageDto);
}
