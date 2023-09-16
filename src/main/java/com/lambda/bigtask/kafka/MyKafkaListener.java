package com.lambda.bigtask.kafka;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MyKafkaListener {

    private final static Integer bCountPerA = 40;

    @KafkaListener(id = "doBi", groupId = "testgroup", topics = {"doBi"})
    @Transactional(rollbackFor = {Exception.class})
    public void listener1(String message) {
        KafkaEntity msg = JSON.parseObject(message, KafkaEntity.class);
        if ("doBi".equals(msg.getStatus())) {
            for (int i=0; i<bCountPerA; i++) {
                this.doBiJob();
            }
        }
    }
    private void doBiJob() {
        // do BiJob
    }
}