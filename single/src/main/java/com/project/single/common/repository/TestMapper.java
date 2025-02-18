package com.project.single.common.repository;

import com.project.single.common.model.TestModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    List<TestModel> selectZone();
}
