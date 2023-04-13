package com.example.demo.controller;

import com.example.demo.entity.DemoTest;
import com.example.demo.service.DemoTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/test")
public class DemoTestController {

    @Autowired(required = false)
    DemoTestService demoTestService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Object findAll() {
        return demoTestService.findAll();
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public Object findAll(Integer id) {
        return demoTestService.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Object test() {
        return "asd";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public Object errorTest() {
        throw new RuntimeException("test runtimeException");
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Object add() {
        DemoTest demoTest = new DemoTest();
        demoTest.setName("javersAdd");
        demoTest.setCreateDate(new Date());
        return demoTestService.save(demoTest);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public Object edit(Integer id, String name) {
        DemoTest demoTest = demoTestService.findById(id);
        demoTest.setName(name);
        demoTest.setUpdateDate(new Date());
        return demoTestService.update(demoTest);
    }

    @RequestMapping(value = "/del", method = RequestMethod.GET)
    public Object del(Integer id) {
        demoTestService.delete(id);
        return "success";
    }
}
