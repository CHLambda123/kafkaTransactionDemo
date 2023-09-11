package com.lambda.bigtask.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("Btask")
@Data
public class BEntity {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @TableField("task_name")
    private String taskName;
    public BEntity(String taskName) {
        this.taskName = taskName;
    }
}
