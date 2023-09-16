package com.lambda.bigtask.kafka;

import com.alibaba.fastjson.JSON;
import com.lambda.bigtask.entity.BEntity;
import com.lambda.bigtask.mapper.BMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MyKafkaListener {
    @Autowired
    private BMapper bMapper;
    private final static Integer bCountPerA = 40;

    @KafkaListener(id = "doAi", groupId = "testgroup", topics = {"doAi"})
    @Transactional(rollbackFor = {Exception.class})
    public void doAiListener(String message) {
        KafkaEntity msg = JSON.parseObject(message, KafkaEntity.class);
        if ("doAiJob".equals(msg.getStatus())) {
            this.doAiJob();
            Long id = Long.valueOf(msg.getId().substring(6));
            for (int j=0; j<bCountPerA; j++) {
                bMapper.insert(new BEntity("Btask-"+id+"-"+j, "not_yet"));
            }
        }
    }
    @KafkaListener(id = "doBi", groupId = "testgroup", topics = {"doBi"})
    @Transactional(rollbackFor = {Exception.class})
    public void doBiListener(String message) {
        KafkaEntity msg = JSON.parseObject(message, KafkaEntity.class);
        if ("doBiJob".equals(msg.getStatus())) {
            this.doBiJob();
        }
    }
    private void doAiJob() {
        // do Ai job
    }
    private void doBiJob() {
        // do Bi job
    }
}