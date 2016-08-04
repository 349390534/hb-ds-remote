/**
 * 
 */
package com.howbuy.uaa.remote.dao.base;

import java.util.List;
import java.util.Map;

/**
 * @author qiankun.li
 * 
 */
public interface IBaseDao<T, PK> {

	int insert(String sqlId, T t);
	
	int insert(String sqlId, List<T> list);

	int udpate(String sqlId, T t);

	List<T> query(String sqlId, T t);
	
	List<T> queryPage(String sqlId,Map<String,Object> map);

	int deleteById(String sqlId, PK pk);
	
	int delete(String sqlId,T t);
	
	<M> int executeDelete(String sqlId,M m);
	
	<M> M selectOne(String sqlId,Map<String,Object> map);
}
