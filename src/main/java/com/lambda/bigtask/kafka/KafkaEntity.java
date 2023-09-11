package com.lambda.bigtask.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
@Data
@AllArgsConstructor
public class KafkaEntity implements Serializable {
    private String id;
    private String status;
}
