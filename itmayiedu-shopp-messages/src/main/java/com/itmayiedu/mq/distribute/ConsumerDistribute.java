
package com.itmayiedu.mq.distribute;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.itmayiedu.adapter.MessageAdapter;
import com.itmayiedu.constants.MQInterfaceType;
import com.itmayiedu.service.SMSMailboxService;
import com.netflix.discovery.converters.Auto;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @classDesc: 功能描述:(消费消息 mq 从队列获取最新消息)
 * @author: 蚂蚁课堂创始人-余胜军
 * @QQ: 644064779
 * @QQ粉丝群: 116295598
 * @createTime: 2017年10月25日 上午12:09:45
 * @version: v1.0
 * @copyright:每特学院(蚂蚁课堂)上海每特教育科技有限公司
 */
@Slf4j
@Component
public class ConsumerDistribute {
	@Autowired
	private SMSMailboxService smsMailboxService;

	@JmsListener(destination = "mail_queue")
	public void distribute(String json) {
		log.info("###消息服务###收到消息,消息内容 json:{}", json);
		if (StringUtils.isEmpty(json)) {
			return;
		}
		JSONObject jsonObject = new JSONObject().parseObject(json);
		JSONObject header = jsonObject.getJSONObject("header");
		String interfaceType = header.getString("interfaceType");
		MessageAdapter messageAdapter = null;
		switch (interfaceType) {
		// 发送邮件
		case MQInterfaceType.SMS_MAIL:
			messageAdapter=smsMailboxService;
			break;
		default:
			break;
		}
		JSONObject content=jsonObject.getJSONObject("content");
		messageAdapter.distribute(content);

	}

}
