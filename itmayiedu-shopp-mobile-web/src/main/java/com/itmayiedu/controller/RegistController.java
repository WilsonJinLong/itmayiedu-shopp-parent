
package com.itmayiedu.controller;

import java.lang.annotation.Retention;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itmayiedu.base.controller.BaseController;
import com.itmayiedu.constants.BaseApiConstants;
import com.itmayiedu.entity.UserEntity;
import com.itmayiedu.feign.UserFeign;

@Controller
public class RegistController extends BaseController {
	private static final String LOCAREGIST = "locaRegist";
	private static final String LGOIN = "login";
	@Autowired
	private UserFeign userFeign;

	@RequestMapping("/locaRegist")
	public String locaRegist() {
		return LOCAREGIST;
	}

	@RequestMapping("/regist")
	public String regist(@ModelAttribute("user") UserEntity userEntity, HttpServletRequest request,
			HttpSession httpSession) {
		try {
			String openid = (String) httpSession.getAttribute("openid");
			if (!StringUtils.isEmpty(openid)) {
				userEntity.setOpenId(openid);
			}
			Map<String, Object> registMap = userFeign.regist(userEntity);
			Integer code = (Integer) registMap.get(BaseApiConstants.HTTP_CODE_NAME);
			if (!code.equals(BaseApiConstants.HTTP_200_CODE)) {
				String msg = (String) registMap.get("msg");
				return setError(request, msg, LOCAREGIST);
			}
			// 注册成功
			return LGOIN;

		} catch (Exception e) {
			return setError(request, "注册失败!", LOCAREGIST);
		}
	}

}
