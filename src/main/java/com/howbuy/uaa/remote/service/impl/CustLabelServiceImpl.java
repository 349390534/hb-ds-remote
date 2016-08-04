package com.howbuy.uaa.remote.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.howbuy.uaa.remote.dao.CustLabelDao;
import com.howbuy.uaa.remote.service.CustLabelService;

@Service
public class CustLabelServiceImpl implements CustLabelService {
	
	@Autowired
	private CustLabelDao dao;

	@Override
	public Map<Object,Object> getMapping() {
		return dao.queryFieldMapping();
	}

	@Override
	public List<?> query() {
		
		return dao.query();
	}

}
