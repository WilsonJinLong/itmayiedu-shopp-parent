package com.itmayiedu.service.pay.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.itmayiedu.entity.PaymentInfo;
import com.itmayiedu.entity.PaymentType;
import com.itmayiedu.feign.PaymentTypeFeign;
import com.itmayiedu.service.pay.PayAdaptService;
import com.itmayiedu.service.pay.PayService;
import com.itmayiedu.service.pay.yinlian.YinLianPay;
import com.itmayiedu.utils.ResultUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public   class PayImplService implements PayService {
	@Autowired
	private PaymentTypeFeign paymentTypeFeign;
	@Autowired
    private YinLianPay yinLianPay;
	@Override
	public String pay(PaymentInfo paymentInfo) {
		Long typeId = paymentInfo.getTypeId();
		Map<String, Object> paymentTypeMap = paymentTypeFeign.getPaymentType(typeId);
		if (paymentTypeMap == null) {
			log.error("####pay() typeId:{},paymentTypeMap is null");
			return null;
		}
		Map<String, Object> data = (Map<String, Object>) ResultUtils.getResultMap(paymentTypeMap);
		String json = new JSONObject().toJSONString(data);
		PaymentType paymentType = new JSONObject().parseObject(json, PaymentType.class);
		if (paymentType == null) {
			return null;
		}
		String typeName = paymentType.getTypeName();
		PayAdaptService payAdaptService=null;
		switch (typeName) {
		case "yinlianPay":
			payAdaptService=yinLianPay;
			break;

		default:
			break;
		}
		return payAdaptService.pay(paymentInfo, paymentType);
	}

}
