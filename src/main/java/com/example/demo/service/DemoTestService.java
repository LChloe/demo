package com.example.demo.service;

import com.example.demo.base.service.BaseService;
import com.example.demo.entity.DemoTest;
import com.example.demo.repository.DemoTestRepository;
import org.springframework.stereotype.Service;

@Service
public class DemoTestService extends BaseService<DemoTestRepository, DemoTest, Integer> {

    public void insert(String name){
        DemoTest demoTest = new DemoTest();
        demoTest.setName(name);
        name.hashCode();
        getBaseRepository().save(demoTest);
    }

    public DemoTest findById(Integer id){
        return find(id);
    }

}
