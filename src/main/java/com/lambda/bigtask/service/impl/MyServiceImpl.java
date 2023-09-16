package com.lambda.bigtask.service.impl;

import com.lambda.bigtask.entity.AEntity;
import com.lambda.bigtask.mapper.AMapper;
import com.lambda.bigtask.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyServiceImpl implements MyService {
    @Autowired
    private AMapper aMapper;

    private final static Integer aCount = 1000;
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public String getKafkaTime() {
        long startTime = System.currentTimeMillis();
        for (int i=0; i<aCount; i++) {
            aMapper.insert(new AEntity("taskA-"+i, "not_yet"));
        }
        long endTime = System.currentTimeMillis();
        return String.valueOf((endTime-startTime)/1000);
    }
}
