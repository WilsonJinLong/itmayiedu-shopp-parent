package com.itmayiedu.service.impl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itmayiedu.common.api.BaseApiService;
import com.itmayiedu.common.redis.BaseRedisService;
import com.itmayiedu.common.token.TokenUtils;
import com.itmayiedu.constants.DBTableName;
import com.itmayiedu.dao.PaymentInfoDao;
import com.itmayiedu.entity.PaymentInfo;
import com.itmayiedu.service.PaymentInfoService;
import com.itmayiedu.utils.DateUtils;

@RestController
@RequestMapping("/pay")
public class PaymentInfoServiceImpl extends BaseApiService implements PaymentInfoService {
	@Autowired
	private PaymentInfoDao paymentInfoDao;
	@Autowired
	private BaseRedisService baseRedisService;
	@Autowired
	private TokenUtils tokenUtils;

	@Override
	public Map<String, Object> addPayInfoToken(@RequestBody PaymentInfo paymentInfo) {
		paymentInfo.setCreated(DateUtils.getTimestamp());
		paymentInfo.setUpdated(DateUtils.getTimestamp());
		paymentInfoDao.savePaymentType(paymentInfo);
		Long paymentInfoId = paymentInfo.getId();
		if (paymentInfoId == null) {
			return setResutError("系统错误,为获取到支付id");
		}
		String token = tokenUtils.getPayToken();
		baseRedisService.setString(token, paymentInfoId + "");
		return setResutSuccessData(token);
	}

	@Override
	public Map<String, Object> getPayInfoToken(@RequestParam("token") String token) {
		if (StringUtils.isEmpty(token)) {
			return setResutError("token不能为空!");
		}
		String payInfoId = baseRedisService.get(token);
		Long newPayInfoId = Long.parseLong(payInfoId);
		PaymentInfo paymentInfo = paymentInfoDao.getPaymentInfo(newPayInfoId);
		return setResutSuccessData(paymentInfo);
	}

	@Override
	public Map<String, Object> getByOrderIdPayInfo(@RequestParam("orderId")String orderId) {
		PaymentInfo paymentInfo = paymentInfoDao.getByOrderIdPayInfo(orderId);
		if(paymentInfo==null){
			return setResutError("未查询到支付信息");
		}
		return setResutSuccessData(paymentInfo);
	}

	@Override
	public Map<String, Object> updatePayInfo(@RequestBody PaymentInfo paymentInfo) {
		paymentInfoDao.updatePayInfo(paymentInfo);
		return setResutSuccess();
	}

}
