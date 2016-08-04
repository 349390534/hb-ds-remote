/**
 * 
 */
package com.howbuy.uaa.remote.dto.simu;

import java.util.List;

/**
 * @author qiankun.li 通用返回对象
 */
public class ResponseData<T> {

	/**
	 * 状态码 1失败，0成功
	 */
	private String status;
	/**
	 * 描述信息
	 */
	private String desc;
	/**
	 * 返回集合
	 */
	private List<T> response;

	/**
	 * 集合size
	 */
	private long count;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<T> getResponse() {
		return response;
	}

	public void setResponse(List<T> response) {
		this.response = response;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

}
