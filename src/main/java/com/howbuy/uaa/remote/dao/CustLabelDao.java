/**
 * 
 */
package com.howbuy.uaa.remote.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.howbuy.uaa.remote.dao.base.BaseDao;
import com.howbuy.uaa.remote.persistence.ChannelAdClick;

/**
 * @author qiankun.li
 *
 */
@Repository
public class CustLabelDao extends BaseDao {

	@Override
	protected String setNameSpace() {
		return "label_oracle";
	}

	@SuppressWarnings("unchecked")
	public Map<Object,Object> queryFieldMapping(){
		String sqlId = "select_cust_label";
		Map<Object,Object> m =queryMap(sqlId, null, "tagCode");
		return m;
	}

	public List query(){
		List<ChannelAdClick> result = new ArrayList<ChannelAdClick>();
		String sqlId = "select_cust_label2";
		
		return queryList(sqlId, new Object());
	}
}
