package com.itmayiedu.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.itmayiedu.api.service.ItemDescService;

@FeignClient("commodity")
public interface ItemDescFeign extends ItemDescService {

}
