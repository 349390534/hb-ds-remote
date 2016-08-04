/**
 * 
 */
package com.howbuy.uaa.remote.dao.base;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author qiankun.li
 *
 */
public abstract class BaseDao<T,PK> implements IBaseDao<T,PK>{

	@Autowired@Qualifier("uaaSqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Autowired@Qualifier("oraceSqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplateOracle;

	public void setSqlSession(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	protected abstract String setNameSpace() ;
	
	protected String getNameSpace(String sqlId){
		return setNameSpace().concat(".").concat(sqlId);
	}

	@Override
	public int insert(String sqlId,T t) {
		int i = sqlSessionTemplate.insert(getNameSpace(sqlId), t);
		return i;
	}


	@Override
	public int insert(String sqlId, List<T> list) {
		int i = sqlSessionTemplate.insert(getNameSpace(sqlId), list);
		return i;
	}

	@Override
	public int udpate(String sqlId,T t) {
		int i = sqlSessionTemplate.update(getNameSpace(sqlId), t);
		return i;
	}


	@Override
	public List<T> query(String sqlId,T t) {
		List<T> list = sqlSessionTemplate.selectList(getNameSpace(sqlId), t);
		return list;
	}

	public <M> List<M> queryList(String sqlId,M m) {
		List<M> list = sqlSessionTemplate.selectList(getNameSpace(sqlId), m);
		return list;
	}
	
	public <M> List<M> queryList(String sqlId,Map<String, Object> paramMap) {
		List<M> list = sqlSessionTemplate.selectList(getNameSpace(sqlId), paramMap);
		return list;
	}
	
	public <M> List<M> queryList(String sqlId,List<T> tList) {
		List<M> list = sqlSessionTemplate.selectList(getNameSpace(sqlId), tList);
		return list;
	}
	
	public Map<Object,Object> queryMap(String sqlId,Map<String, Object> paramMap,String mapKey){
		
		return sqlSessionTemplateOracle.selectMap(getNameSpace(sqlId), null, mapKey);
	}
	
	@Override
	public int deleteById(String sqlId,PK pk) {
		int i =sqlSessionTemplate.delete(getNameSpace(sqlId), pk);
		return i;
	}

	@Override
	public int delete(String sqlId,T t) {
		int i =sqlSessionTemplate.delete(getNameSpace(sqlId), t);
		return i;
	}
	
	@Override
	public <M> int executeDelete(String sqlId,M m) {
		int i =sqlSessionTemplate.delete(getNameSpace(sqlId), m);
		return i;
	}

	@Override
	public List<T> queryPage(String sqlId, Map<String, Object> map) {
		if(null!=map){
			List<T> list = sqlSessionTemplate.selectList(getNameSpace(sqlId), map);
			return list;
		}
		return null;
	}

	@Override
	public <M> M selectOne(String sqlId,Map<String, Object> map) {
		return sqlSessionTemplate.selectOne(getNameSpace(sqlId), map);
	}
	
	
	
}
