package com.lambda.bigtask.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("tableB")
@Data
public class BEntity {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @TableField("task_name")
    private String taskName;
    @TableField("status")
    private String status;
    public BEntity(String taskName, String status) {
        this.taskName = taskName;
        this.status = status;
    }
}
