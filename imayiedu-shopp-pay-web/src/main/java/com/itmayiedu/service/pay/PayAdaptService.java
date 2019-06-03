package com.itmayiedu.service.pay;

import com.itmayiedu.entity.PaymentInfo;
import com.itmayiedu.entity.PaymentType;

public interface PayAdaptService {
	public String pay(PaymentInfo paymentInfo,PaymentType paymentType );
}
