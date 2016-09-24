package com.quanquan.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Quanquan on 2016/9/23.
 */
@Controller
@RequestMapping("/mvc")
public class mvcController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}
