package com.poss.controller;

import com.poss.feign.BusinessFeign;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * @title TestController
 * @description
 * @author poss
 * @version 1.0.0
 * @create 2023/11/11 18:50
 **/
@RestController
public class TestController {
    
    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);
    
    @Resource
    BusinessFeign businessFeign;

    @GetMapping("/hello")
    public String testController(){
        String businessHello = businessFeign.hello();
        LOG.info(businessHello);
        return "hello world!!!  Batch!";
    }
}
