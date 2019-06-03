package com.itmayiedu.service;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itmayiedu.entity.PaymentInfo;

/**
 * 支付接口
 * 
 * @author Administrator
 *
 */
@RequestMapping("/pay")
public interface PaymentInfoService {

	/**
	 * 换取支付令牌
	 * 
	 * @return
	 */
	@RequestMapping("/addPayInfoToken")
	public Map<String, Object> addPayInfoToken(@RequestBody PaymentInfo paymentInfo);

	/**
	 * 使用token查找支付信息
	 * 
	 * @param paymentInfo
	 * @return
	 */
	@RequestMapping("/getPayInfoToken")
	public Map<String, Object> getPayInfoToken(@RequestParam("token") String token);

	/**
	 * 使用token查找支付信息
	 * 
	 * @param paymentInfo
	 * @return
	 */
	@RequestMapping("/getByOrderIdPayInfo")
	public Map<String, Object> getByOrderIdPayInfo(@RequestParam("orderId") String orderId);
	
	/**
	 * 使用token查找支付信息
	 * 
	 * @param paymentInfo
	 * @return
	 */
	@RequestMapping("/updatePayInfo")
	public Map<String, Object> updatePayInfo(@RequestBody PaymentInfo paymentInfo);
}
