/**
 * 
 */
package com.howbuy.uaa.remote.dto.simu;

import com.howbuy.uaa.remote.common.QueryPageType;

/**
 * @author qiankun.li
 * 
 */
public class ChannelPageDto {

	/**
	 * pageNum 默认1
	 */
	private Integer pageNum = 1;
	/**
	 * 查询的数据类型
	 * 
	 * @see QueryPageType
	 */
	private String pageType;
	/**
	 * 开始日期
	 */
	private String beginDate;
	/**
	 * 结束日期
	 */
	private String endDate;
	/**
	 * 查询天数，截止今天，向前推的天数
	 */
	private Integer days;
	/**
	 * 倒序排列,查询前多少条
	 */
	private Integer topNum;

	/**
	 * 模块id 1：私募,2资讯
	 */
	private String proid;

	/**
	 * id列表
	 */
	private String ids;

	/**
	 * 
	 */
	private String subtype;
	/**
	 * 
	 */
	private String newsType;
	/**
	 * 
	 */
	private String author;

	/**
	 * pageid
	 */
	private String pageId;

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

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Integer getTopNum() {
		return topNum;
	}

	public void setTopNum(Integer topNum) {
		this.topNum = topNum;
	}

	public String getProid() {
		return proid;
	}

	public void setProid(String proid) {
		this.proid = proid;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public String getSubtype() {
		return subtype;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	public String getNewsType() {
		return newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

}
