package com.itmayiedu.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.itmayiedu.service.PaymentTypeService;

@FeignClient("pay-service")
public interface PaymentTypeFeign extends PaymentTypeService {

}
