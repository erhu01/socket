package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: xiong
 * @Date: 2020/11/9 13:45
 */
@Controller
public class TestController {


    @RequestMapping("test")
    public String test (){
        return "test";
    }
}
