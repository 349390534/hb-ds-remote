package com.howbuy.uaa.remote.service;

import java.util.List;
import java.util.Map;

/**
 * 客户标签服务类 
 */
public interface CustLabelService {

	/**
	 * 获取客户solr字段映射 
	 * @return
	 */
	Map<Object,Object> getMapping();
	List<?> query();
}
