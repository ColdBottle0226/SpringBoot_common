package com.project.single.common.service.impl;

import com.project.single.common.model.TestModel;
import com.project.single.common.repository.TestMapper;
import com.project.single.common.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    TestMapper testMapper;

    @Override
    public List<TestModel> selectZone() {
//        try{
//            throw new CustomException("에러 발생");
//        } catch (CustomException e) {
//            throw e;
//        }
        return testMapper.selectZone();
    }
}
