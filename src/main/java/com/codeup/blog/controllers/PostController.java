package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {
    @GetMapping("/posts")
    @ResponseBody
    public String postsIndex(){
        return "posts index page";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String singlePost(@PathVariable long id){
        return "view an individual post with id of: " + id;
    }

    @GetMapping("posts/create")
    @ResponseBody
    public String createPostForm(){
        return "view the form for creating a post";
    }

    @PostMapping("posts/create")
    @ResponseBody
    public String createPost(){
        return "create a new post";
    }

}
