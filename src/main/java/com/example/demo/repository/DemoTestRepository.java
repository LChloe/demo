package com.example.demo.repository;

import com.example.demo.aop.annotation.JaversExpandInfo;
import com.example.demo.base.repository.IBaseRepository;
import com.example.demo.entity.DemoTest;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@JaversSpringDataAuditable
@JaversExpandInfo(desc = "模版类", name = "DemoTest")
public interface DemoTestRepository extends IBaseRepository<DemoTest, Integer> {


    @Query(value = "SELECT * FROM demo_test a",nativeQuery = true)
    List<DemoTest> findAll1();

    @Transactional
    @Modifying
    @Query("delete from DemoTest a where a.id in (:ids)")
    void delete(@Param("ids") List<Integer> ids);
}
