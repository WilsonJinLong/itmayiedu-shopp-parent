
package com.itmayiedu.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itmayiedu.base.controller.BaseController;
import com.itmayiedu.constants.BaseApiConstants;
import com.itmayiedu.constants.Constants;
import com.itmayiedu.entity.UserEntity;
import com.itmayiedu.feign.UserFeign;
import com.itmayiedu.web.CookieUtil;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.oauth.Oauth;

@Controller
public class LoginConroller extends BaseController {
	private static final String LGOIN = "login";
	private static final String INDEX = "index";
	private static final String ASSOCIATEDACCOUNT = "associatedAccount";
	@Autowired
	private UserFeign userFeign;

	@RequestMapping("/locaLogin")
	public String locaLogin(HttpServletRequest request, String source) {
		request.setAttribute("source", source);
		return LGOIN;
	}

	@RequestMapping("/login")
	public String login(@ModelAttribute("user") UserEntity userEntity, @ModelAttribute("source") String source,
			HttpServletRequest request, HttpSession httpSession, HttpServletResponse response) {
		if (!StringUtils.isEmpty(source)) {
			String openid = (String) httpSession.getAttribute("openid");
			userEntity.setOpenId(openid);
		}
		Map<String, Object> login = userFeign.login(userEntity);
		Integer code = (Integer) login.get(BaseApiConstants.HTTP_CODE_NAME);
		if (!code.equals(BaseApiConstants.HTTP_200_CODE)) {
			String msg = (String) login.get("msg");
			return setError(request, msg, LGOIN);
		}
		// 登录成功之后,获取token.将这个token存放在cookie
		String token = (String) login.get("data");
		CookieUtil.addCookie(response, Constants.USER_TOKEN, token, Constants.WEBUSER_COOKIE_TOKEN_TERMVALIDITY);
		return INDEX;

	}

	/**
	 * 跳转到QQ授权地址
	 * 
	 * @param request
	 * @return
	 * @throws QQConnectException
	 */
	@RequestMapping("/locaQQLogin")
	public String locaQQLogin(HttpServletRequest request) throws QQConnectException {
		String authorizeURL = new Oauth().getAuthorizeURL(request);
		return "redirect:" + authorizeURL;
	}

	/**
	 * QQ回调地址
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/qqLoginCallback")
	public String qqLoginCallback(HttpServletRequest request, HttpSession httpSession, HttpServletResponse response) {
		try {
			// 先获取accessToken
			AccessToken accessTokenObj = new Oauth().getAccessTokenByRequest(request);
			if (accessTokenObj == null) {
				return setError(request, "qq授权失败!", ERROR);
			}
			String accessToken = accessTokenObj.getAccessToken();
			if (StringUtils.isEmpty(accessToken)) {
				return setError(request, "qq授权失败!", ERROR);
			}
			// 获取openid
			OpenID openIdObj = new OpenID(accessToken);
			String userOpenID = openIdObj.getUserOpenID();
			// 使用openid查找用户信息是否绑定
			Map<String, Object> openIdMap = userFeign.findLogin(userOpenID);
			Integer code = (Integer) openIdMap.get(BaseApiConstants.HTTP_CODE_NAME);
			// 判断是否绑定会员
			if (code.equals(BaseApiConstants.HTTP_200_CODE)) {
				// 已经授权过,自动登录
				String token = (String) openIdMap.get("data");
				CookieUtil.addCookie(response, Constants.USER_TOKEN, token,
						Constants.WEBUSER_COOKIE_TOKEN_TERMVALIDITY);
				return INDEX;
			}
			// 没有绑定openid
			httpSession.setAttribute("openid", userOpenID);
		} catch (Exception e) {
		}
		// 跳转到关联页面
		return ASSOCIATEDACCOUNT;
	}

}
