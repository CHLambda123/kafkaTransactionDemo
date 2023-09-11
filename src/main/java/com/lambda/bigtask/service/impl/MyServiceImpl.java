package com.lambda.bigtask.service.impl;

import com.lambda.bigtask.entity.AEntity;
import com.lambda.bigtask.entity.BEntity;
import com.lambda.bigtask.mapper.AMapper;
import com.lambda.bigtask.mapper.BMapper;
import com.lambda.bigtask.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyServiceImpl implements MyService {
    @Autowired
    private AMapper aMapper;
    @Autowired
    private BMapper bMapper;
    private final static Integer aCount = 1000;
    private final static Integer bCountPerA = 40;
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public String getSingleThreadTime() {
        long startTime = System.currentTimeMillis();
        for (int i=0; i<aCount; i++) {
            aMapper.insert(new AEntity("singleThread-"+i, "done"));
            if (i==6) {
                int kkk = 1/0;
            }
            for (int j=0; j<bCountPerA; j++) {
                bMapper.insert(new BEntity("singleThread-"+i+"-"+j));
            }
        }
        long endTime = System.currentTimeMillis();
        return String.valueOf((endTime-startTime)/1000);
    }
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public String getKafkaTime() {
        long startTime = System.currentTimeMillis();
        for (int i=0; i<aCount; i++) {
            aMapper.insert(new AEntity("kafka-"+i, "not_yet"));
        }
        long endTime = System.currentTimeMillis();
        return String.valueOf((endTime-startTime)/1000);
    }
}
