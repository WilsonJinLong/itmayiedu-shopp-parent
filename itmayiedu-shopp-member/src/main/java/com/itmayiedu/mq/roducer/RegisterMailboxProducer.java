
package com.itmayiedu.mq.roducer;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * 
 * @classDesc: 功能描述:(往消息服务 推送 邮件信息)
 * @author: 蚂蚁课堂创始人-余胜军
 * @QQ: 644064779
 * @QQ粉丝群: 116295598
 * @createTime: 2017年10月24日 下午11:40:45
 * @version: v1.0
 * @copyright:每特学院(蚂蚁课堂)上海每特教育科技有限公司
 */
@Service("registerMailboxProducer")
public class RegisterMailboxProducer {
	@Autowired
    private JmsMessagingTemplate jmsMessagingTemplate ; 
	
	public void send(Destination destination,String json){
		jmsMessagingTemplate.convertAndSend(destination, json);
	}
}
