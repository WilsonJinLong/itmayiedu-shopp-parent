package com.itmayiedu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.itmayiedu.base.controller.BaseController;
import com.itmayiedu.entity.PaymentInfo;
import com.itmayiedu.feign.PaymentInfoFeign;
import com.itmayiedu.service.pay.impl.PayImplService;
import com.itmayiedu.utils.ResultUtils;

@Controller
public class PayController   extends BaseController {
	@Autowired
	private PaymentInfoFeign paymentInfoFeign;
	private static final String PAYGATEWAY = "payGateway";
	@Autowired
	private PayImplService payService;

	@RequestMapping("/payGateway")
	public void payGateway(HttpServletRequest request, String token,HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html;charset=utf-8");
		Map<String, Object> payInfoToken = paymentInfoFeign.getPayInfoToken(token);
		Map<String, Object> resultMap = (Map<String, Object>) ResultUtils.getResultMap(payInfoToken);
		PrintWriter out=resp.getWriter();
		if (resultMap == null) {
			resp.getWriter().write("系统错误!");
			 return ;
		}
		String json = new JSONObject().toJSONString(resultMap);
		PaymentInfo paymentInfo = new JSONObject().parseObject(json, PaymentInfo.class);
		String html = payService.pay(paymentInfo);
		out.println(html);
		out.close();
	}

}
