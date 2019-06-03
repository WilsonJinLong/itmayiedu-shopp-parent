package com.itmayiedu.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.itmayiedu.service.PaymentInfoService;

@FeignClient("pay-service")
public interface PaymentInfoFeign extends PaymentInfoService {

}
