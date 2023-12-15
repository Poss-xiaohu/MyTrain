package com.poss.controller;

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

    @GetMapping("/hello")
    public String testController(){
        return "hello world!!!  Batch!";
    }
}
