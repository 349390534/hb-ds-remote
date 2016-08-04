/**
 * 
 */
package com.howbuy.uaa.remote.common;

/**
 * @author qiankun.li
 * 
 */
public enum QueryPageType {
	PAGE_FUND("12","私募基金"),
	PAGE_COMPANY("22","私募公司"),
	PAGE_MANAGER("32","私募经理"),
	;

	private String index;
	private String name;

	private QueryPageType(String index, String name) {
		this.index= index;
		this.name= name;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static QueryPageType findByIndex(String index){
		QueryPageType[] types = QueryPageType.values();
		for(QueryPageType qt:types){
			if(qt.getIndex().equals(index)){
				return qt;
			}
		}
		return null;
	}
}
