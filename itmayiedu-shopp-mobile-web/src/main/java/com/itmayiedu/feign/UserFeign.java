
package com.itmayiedu.feign;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itmayiedu.api.service.UserService;


@FeignClient("member")
public interface UserFeign  extends UserService {
//	/**
//	 * 
//	 * @methodDesc: 功能描述:(使用token查找用户信息)
//	 * @param: @param
//	 *             token
//	 * @param: @return
//	 */
//	@PostMapping("/member/getUser")
//	public Map<String, Object> getUser(@RequestParam("token") String token);
}
