package com.example.demo.controller;

import com.example.demo.base.result.ResponseResult;
import com.example.demo.entity.DemoTest;
import com.example.demo.service.RedisService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redis")
public class RedisTestController {

//    // 注意：这里@Autowired是报错的，因为@Autowired按照类名注入的
//    @Resource
//    private RedisTemplate<String, DemoTest> redisTemplate;
    @Autowired
    private RedisService<DemoTest> redisService;

    /**
     * @param user user param
     * @return user
     */
    @ApiOperation("Add")
    @PostMapping("add")
    public ResponseResult<DemoTest> add(DemoTest user) {
        redisService.set(String.valueOf(user.getId()), user);
        return ResponseResult.success(redisService.get(String.valueOf(user.getId())));
    }

    @ApiOperation("Add2")
    @PostMapping("add2")
    public ResponseResult<DemoTest> add2(@RequestBody DemoTest user) {
        redisService.set(String.valueOf(user.getId()), user);
        return ResponseResult.success(redisService.get(String.valueOf(user.getId())));
    }

    /**
     * @return user list
     */
    @ApiOperation("Find")
    @GetMapping("find/{userId}")
    public ResponseResult<DemoTest> edit(@PathVariable("userId") String userId) {
        return ResponseResult.success(redisService.get(userId));
    }

}
