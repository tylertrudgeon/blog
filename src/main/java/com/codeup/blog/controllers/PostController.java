package com.codeup.blog.controllers;

import com.codeup.blog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class PostController {
    @GetMapping("/posts")
    public String postsIndex(Model model){
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(new Post("Second post", "Second body"));
        posts.add(new Post("Third post", "Third body"));
        model.addAttribute("noPostsFound", posts.size() == 0);
        model.addAttribute("posts", posts);
        return "/posts/index";
    }

    @GetMapping("/posts/show")
    public String singlePost(Model model){
        Post post = new Post("title", "body goes here");
        model.addAttribute("post", post);
        return "posts/show";
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
