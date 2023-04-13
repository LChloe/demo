package com.example.demo.controller;

import com.example.demo.entity.DemoTest;
import com.example.demo.service.DemoTestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api
@CrossOrigin
@RestController
@RequestMapping("/testSwagger")
public class SwaggerController {

    @Autowired(required = false)
    DemoTestService DemoTestService;

    @ApiOperation("Query all demo_test")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
//    @ApiImplicitParam(name = "DemoTest", type = "body", dataTypeClass = DemoTest.class, required = true)
    public List<DemoTest> findAll() {
        return DemoTestService.findAll();
    }

    @ApiOperation("Query one demo_test")
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public DemoTest findAll(Integer id) {
        return DemoTestService.findById(id);
    }

}
