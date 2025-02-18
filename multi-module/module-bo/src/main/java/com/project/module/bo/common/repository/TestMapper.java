package com.project.module.bo.common.repository;

import com.project.module.bo.common.model.TestModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    List<TestModel> selectZone();
}
