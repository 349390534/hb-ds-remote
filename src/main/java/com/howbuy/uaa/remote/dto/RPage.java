/**
 * 
 */
package com.howbuy.uaa.remote.dto;

import java.util.List;

/**
 * @author qiankun.li
 * 
 */
public class RPage<T> {

	private int pageIndex;
	private long count;
	private List<T> response;

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public List<T> getResponse() {
		return response;
	}

	public void setResponse(List<T> response) {
		this.response = response;
	}

}
