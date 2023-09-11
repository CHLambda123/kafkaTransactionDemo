package com.lambda.bigtask.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("Atask")
@Data
public class AEntity {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    @TableField("task_name")
    private String taskName;
    @TableField("status")
    private String status;
    public AEntity(String taskName, String status) {
        this.taskName = taskName;
        this.status = status;
    }
}
