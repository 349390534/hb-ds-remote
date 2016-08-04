/**
 * 
 */
package com.howbuy.uaa.remote.action;

import javax.servlet.http.HttpServletResponse;

import com.howbuy.uaa.remote.util.JsonParse;


/**
 * @author qiankun.li
 *
 */
public abstract class RemoteBaseAction {

	protected void writeJson(HttpServletResponse response,Object result) {
		JsonParse.writeJson(response, result);
	}
	
}
