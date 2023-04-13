package com.example.demo.repository;

import com.example.demo.aop.annotation.JaversExpandInfo;
import com.example.demo.base.repository.IBaseRepository;
import com.example.demo.entity.DemoTest;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
@JaversExpandInfo(desc = "模版类", name = "DemoTest")
public interface DemoTestRepository extends IBaseRepository<DemoTest, Integer> {
}
