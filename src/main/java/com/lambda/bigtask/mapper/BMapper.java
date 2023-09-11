package com.lambda.bigtask.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lambda.bigtask.entity.BEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BMapper extends BaseMapper<BEntity> {
}
