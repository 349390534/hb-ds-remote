/**
 * 
 */
package com.howbuy.uaa.remote.persistence;


/**
 * @author qiankun.li
 * 广告点击量统计 
 */
public class ChannelAdClick {

	 
	/**
	 * tag广告位
	 */
	private String tag;

	/**
	 * 点击量 
	 */
	private long clickNums;

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public long getClickNums() {
		return clickNums;
	}

	public void setClickNums(long clickNums) {
		this.clickNums = clickNums;
	}

}
