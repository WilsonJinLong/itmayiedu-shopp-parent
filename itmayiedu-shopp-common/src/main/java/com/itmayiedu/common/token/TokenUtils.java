
package com.itmayiedu.common.token;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class TokenUtils {

	public String getToken() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 获取支付token
	 * @return
	 */
	public String getPayToken() {
		return "pay-" + UUID.randomUUID().toString();
	}
}
