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

    @KafkaListener(id = "test1", groupId = "testgroup", topics = {"testtask"})
    @Transactional(rollbackFor = {Exception.class})
    public void listener1(String message) {
        KafkaEntity msg = JSON.parseObject(message, KafkaEntity.class);
        if ("has_no_b_info".equals(msg.getStatus())) {
            Long id = Long.valueOf(msg.getId().substring(6));
            this.doBiJob();
            for (int j=0; j<bCountPerA; j++) {
                bMapper.insert(new BEntity("kafka-"+id+"-"+j));
            }
        }
    }
    private void doBiJob() {
        // do BiJob
    }

}