package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {
    @RequestMapping(path = "/add/{a}/and/{b}")
    @ResponseBody
    public int add(@PathVariable int a, @PathVariable int b){
        return a + b;
    }

    @RequestMapping(path = "/subtract/{a}/from/{b}")
    @ResponseBody
    public int subtract(@PathVariable int a, @PathVariable int b){
        return a - b;
    }

    @RequestMapping(path = "/multiply/{a}/and/{b}")
    @ResponseBody
    public int multiply(@PathVariable int a, @PathVariable int b){
        return a * b;
    }

    @RequestMapping(path = "/divide/{a}/by/{b}")
    @ResponseBody
    public int divide(@PathVariable int a, @PathVariable int b){
        return a / b;
    }
}
