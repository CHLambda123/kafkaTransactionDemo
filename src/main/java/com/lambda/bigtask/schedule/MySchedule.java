package com.lambda.bigtask.schedule;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.lambda.bigtask.entity.AEntity;
import com.lambda.bigtask.entity.BEntity;
import com.lambda.bigtask.kafka.KafkaEntity;
import com.lambda.bigtask.mapper.AMapper;
import com.lambda.bigtask.mapper.BMapper;
import com.lambda.bigtask.service.MyService;
import com.lambda.bigtask.service.impl.MyServiceImpl;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Component
@EnableScheduling
public class MySchedule {
@Autowired
    private KafkaTemplate kafkaTemplate;
    @Autowired
    private AMapper aMapper;
    private final static String topicName = "testtask";
    @Scheduled(fixedRate = 1000)
    @Transactional(rollbackFor = {Exception.class})
    public void sendKafkaMsg() {
        List<AEntity> aEntities = aMapper.selectList(new QueryWrapper<AEntity>().eq("status", "not_yet"));
        for (int i = 0; i < aEntities.size(); i++) {
            this.doAiJob();
            AEntity aEntity = aEntities.get(i);
            aEntity.setStatus("done");
            aMapper.update(aEntity, new QueryWrapper<AEntity>().eq("id", aEntity.getId()));
            kafkaTemplate.send(new ProducerRecord(topicName, JSON.toJSONString(new KafkaEntity("kafka-" + aEntity.getId(), "has_no_b_info"))));
        }
    }
    private void doAiJob() {
        // do Ai Job
    }
}
