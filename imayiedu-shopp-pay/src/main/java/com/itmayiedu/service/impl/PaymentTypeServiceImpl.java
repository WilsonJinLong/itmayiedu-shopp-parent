package com.itmayiedu.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itmayiedu.common.api.BaseApiService;
import com.itmayiedu.dao.PaymentTypeDao;
import com.itmayiedu.entity.PaymentInfo;
import com.itmayiedu.entity.PaymentType;
import com.itmayiedu.service.PaymentTypeService;
import com.sun.jersey.server.impl.model.parameter.BaseParamInjectableProvider;

@Service
@RestController
@RequestMapping("/pay")
public class PaymentTypeServiceImpl extends BaseApiService implements PaymentTypeService {
	@Autowired
	private PaymentTypeDao paymentTypeDao;

	@RequestMapping("/getPaymentType")
	public Map<String, Object> getPaymentType(@RequestParam("id") Long id) {
		PaymentType paymentType = paymentTypeDao.getPaymentType(id);
		if (paymentType == null) {
			return setResutError("未查找到类型");
		}
		return setResutSuccessData(paymentType);
	}

}
