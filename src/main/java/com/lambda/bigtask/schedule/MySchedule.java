package com.lambda.bigtask.schedule;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lambda.bigtask.entity.AEntity;
import com.lambda.bigtask.kafka.KafkaEntity;
import com.lambda.bigtask.mapper.AMapper;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@EnableScheduling
public class MySchedule {
@Autowired
    private KafkaTemplate kafkaTemplate;
    @Autowired
    private AMapper aMapper;
    private final static String topicName = "doBi";
    @Scheduled(fixedRate = 1000)
    @Transactional(rollbackFor = {Exception.class})
    public void sendKafkaMsg() {
        List<AEntity> aEntities = aMapper.selectList(new QueryWrapper<AEntity>().eq("status", "not_yet"));
        for (int i = 0; i < aEntities.size(); i++) {
            this.doAiJob();
            AEntity aEntity = aEntities.get(i);
            aEntity.setStatus("done");
            aMapper.update(aEntity, new QueryWrapper<AEntity>().eq("id", aEntity.getId()));
            kafkaTemplate.send(new ProducerRecord(topicName, JSON.toJSONString(new KafkaEntity("kafka-" + aEntity.getId(), "doBi"))));
        }
    }
    private void doAiJob() {
        // do Ai Job
    }
}
