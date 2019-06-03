
package com.itmayiedu.manage.impl;

import java.lang.annotation.Retention;
import java.util.Map;

import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.deser.Deserializers.Base;
import com.itmayiedu.common.api.BaseApiService;
import com.itmayiedu.common.redis.BaseRedisService;
import com.itmayiedu.common.token.TokenUtils;
import com.itmayiedu.constants.Constants;
import com.itmayiedu.constants.DBTableName;
import com.itmayiedu.constants.MQInterfaceType;
import com.itmayiedu.dao.UserDao;
import com.itmayiedu.entity.UserEntity;
import com.itmayiedu.manage.UserServiceManage;
import com.itmayiedu.mq.roducer.RegisterMailboxProducer;
import com.itmayiedu.utils.DateUtils;
import com.itmayiedu.utils.MD5Util;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceManageImpl extends BaseApiService implements UserServiceManage {
	@Autowired
	private UserDao userDao;
	@Autowired
	private RegisterMailboxProducer registerMailboxProducer;
	@Value("${messages.queue}")
	private String MESSAGES_QUEUE;
	@Autowired
	private TokenUtils tokenUtils;
	@Autowired
	private BaseRedisService baseRedisService;

	@Override
	public void regist(UserEntity userEntity) {
		userEntity.setCreated(DateUtils.getTimestamp());
		userEntity.setUpdated(DateUtils.getTimestamp());
		String phone = userEntity.getPhone();
		String password = userEntity.getPassword();
		userEntity.setPassword(md5PassSalt(phone, password));
		userDao.save(userEntity, DBTableName.TABLE_MB_USER);
		// 队列
		Destination activeMQQueue = new ActiveMQQueue(MESSAGES_QUEUE);
		// 组装报文格式
		String mailMessage = mailMessage(userEntity.getEmail(), userEntity.getUserName());
		log.info("###regist() 注册发送邮件报文mailMessage:{}", mailMessage);
		// mq
		registerMailboxProducer.send(activeMQQueue, mailMessage);
	}

	@Override
	public String md5PassSalt(String phone, String password) {
		String newPass = MD5Util.MD5(phone + password);
		return newPass;

	}

	private String mailMessage(String email, String userName) {
		JSONObject root = new JSONObject();
		JSONObject header = new JSONObject();
		header.put("interfaceType", MQInterfaceType.SMS_MAIL);
		JSONObject content = new JSONObject();
		content.put("mail", email);
		content.put("userName", userName);
		root.put("header", header);
		root.put("content", content);
		return root.toJSONString();
	}

	@Override
	public Map<String, Object> login(UserEntity userEntity) {

		// 往数据进行查找数据
		String phone = userEntity.getPhone();
		String password = userEntity.getPassword();
		String newPassWord = md5PassSalt(phone, password);
		UserEntity userPhoneAndPwd = userDao.getUserPhoneAndPwd(phone, newPassWord);
		if (userPhoneAndPwd == null) {
			return setResutError("账号或密码错误");
		}
		String openId = userEntity.getOpenId();
		if (!StringUtils.isEmpty(openId)) {
			// 关联到数据库中,update
			UserEntity newUserEntity = new UserEntity();
			newUserEntity.setOpenId(openId);
			newUserEntity.setUpdated(DateUtils.getTimestamp());
			userDao.update(newUserEntity, DBTableName.TABLE_MB_USER, userPhoneAndPwd.getId());
		}
		String token = setLoginToken(userEntity.getId());
		return setResutSuccessData(token);

	}

	@Override
	public Map<String, Object> getUser(String token) {
		// 从redis中查找到userid
		String userId = baseRedisService.get(token);
		if (StringUtils.isEmpty(userId)) {
			return setResutError("用户已经过期!");
		}
		Long newUserIdl = Long.parseLong(userId);
		UserEntity userInfo = userDao.getUserInfo(newUserIdl);
		userInfo.setPassword(null);
		return setResutSuccessData(userInfo);
	}

	@Override
	public Map<String, Object> findLogin(String openid) {
		UserEntity userEntity = userDao.findOpenId(openid);
		if (userEntity == null) {
			return setResutError("沒有关联到用户");
		}
		// 自动登录
		String token = setLoginToken(userEntity.getId());
		return setResutSuccessData(token);
	}

	private String setLoginToken(Long userId) {
		// 生成对应的token
		String token = tokenUtils.getToken();
		// key为自定义令牌,用户的userId作作为value 存放在redis中
		baseRedisService.set(token, userId + "", Constants.USER_TOKEN_TERMVALIDITY);
		return token;
	}

}
