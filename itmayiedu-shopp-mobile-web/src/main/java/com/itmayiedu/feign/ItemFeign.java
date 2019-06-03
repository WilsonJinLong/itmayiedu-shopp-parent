package com.itmayiedu.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.itmayiedu.api.service.ItemService;

@FeignClient("commodity")
public interface ItemFeign extends ItemService {

}
