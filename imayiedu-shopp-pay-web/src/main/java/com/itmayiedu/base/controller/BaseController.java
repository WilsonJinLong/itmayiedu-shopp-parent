package com.itmayiedu.base.controller;

import javax.servlet.http.HttpServletRequest;

import com.itmayiedu.common.api.BaseApiService;

public class BaseController extends BaseApiService {
	public static final String ERROR = "common/error";

	public String setError(HttpServletRequest request, String msg, String addres) {
		request.setAttribute("error", msg);
		return addres;
	}
}
