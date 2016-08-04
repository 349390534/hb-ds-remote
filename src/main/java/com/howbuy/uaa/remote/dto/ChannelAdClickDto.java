/**
 * 
 */
package com.howbuy.uaa.remote.dto;

/**
 * @author qiankun.li
 * 
 */
public class ChannelAdClickDto {

	private  String proid;
	/**
	 * 开始日期
	 */
	private String beginDate;
	/**
	 * 结束日期
	 */
	private String endDate;

	private Integer pageIndex;

	private Integer topnum;

	private String tag;

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getTopnum() {
		return topnum;
	}

	public void setTopnum(Integer topnum) {
		this.topnum = topnum;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getProid() {
		return proid;
	}

	public void setProid(String proid) {
		this.proid = proid;
	}

}
