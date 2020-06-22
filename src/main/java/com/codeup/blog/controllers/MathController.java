package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {
    @RequestMapping(path = "/add/3/and/4")
    @ResponseBody
    public int threePlusFour(){
        return 3 + 4;
    }

    @RequestMapping(path = "/subtract/3/from/10")
    @ResponseBody
    public int tenMinusThree(){
        return 10 - 3;
    }

    @RequestMapping(path = "/multiply/4/and/5")
    @ResponseBody
    public int fourTimesFive(){
        return 4 * 5;
    }

    @RequestMapping(path = "/divide/6/by/3")
    @ResponseBody
    public int sixDividedByThree(){
        return 6 / 3;
    }
}
