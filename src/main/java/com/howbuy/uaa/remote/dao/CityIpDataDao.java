/**
 * 
 */
package com.howbuy.uaa.remote.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.howbuy.uaa.remote.dao.base.BaseDao;

/**
 * @author qiankun.li
 *
 */
@Repository
public class CityIpDataDao extends BaseDao {

	@Override
	protected String setNameSpace() {
		return "uac_city_ipdata";
	}

	@SuppressWarnings("unchecked")
	public List<Map<Object,Object>>  queryCityIpDataMap(String beginTime,String endTime){
		String sqlId = "select_ipdata";
		Map<String,Object> map = new java.util.HashMap<String, Object>();
		map.put("beginTime", beginTime);
		map.put("endTime", endTime);
		List<Map<Object,Object>> list =queryList(sqlId, map);
		return list;
	}

	 
}
