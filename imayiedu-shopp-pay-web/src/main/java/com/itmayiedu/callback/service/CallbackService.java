package com.itmayiedu.callback.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface CallbackService {
	/**
	 * 同步回调
	 * 
	 * @return
	 */
	public Map<String, String> syn(HttpServletRequest request);

	/**
	 * 异步回调
	 * 
	 * @return
	 */
	public String asyn(HttpServletRequest request);
}
