package com.qinjinhui.paymentcredit.mq;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;

/**
 * @Author qinjinhui
 * @Date 2023/12/21
 * @Describe
 **/
@RocketMQMessageListener(topic = "delayTopic",consumerGroup="boot-mq-group-consumer")
public class MQMsgLintener implements RocketMQListener<MessageExt> {
    @Override
    public void onMessage(MessageExt messageExt) {

    }
}
