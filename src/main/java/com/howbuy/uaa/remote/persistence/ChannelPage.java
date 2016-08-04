/**
 * 
 */
package com.howbuy.uaa.remote.persistence;


/**
 * @author qiankun.li
 * 
 */
public class ChannelPage {

	 
	/**
	 * pageId
	 */
	private String pageId;

	private long pv;

	private long uv;

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public long getPv() {
		return pv;
	}

	public void setPv(long pv) {
		this.pv = pv;
	}

	public long getUv() {
		return uv;
	}

	public void setUv(long uv) {
		this.uv = uv;
	}
 

}
