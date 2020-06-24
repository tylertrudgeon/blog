package com.codeup.blog.controllers;

import com.codeup.blog.PostsDao.PostsRepository;
import com.codeup.blog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {
    private PostsRepository postsDao;
    public PostController(PostsRepository postsRepository) {
        postsDao = postsRepository;
    }

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

    @GetMapping("/posts/{id}/edit")
    public String showEditForm(Model model, @PathVariable Long id) {
        Post postToEdit = postsDao.getOne(id);
        model.addAttribute("post", postToEdit);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    @ResponseBody
    public String editPost(@PathVariable long id,
                           @RequestParam (name = "title") String title,
                           @RequestParam (name = "body") String body) {
        Post postToEdit = postsDao.getOne(id);
        postToEdit.setTitle(title);
        postToEdit.setBody(body);
        postsDao.save(postToEdit);
        return "Post Updated!";
    }

    @PostMapping("/posts/{id}/delete")
    @ResponseBody
    public String delete(@PathVariable long id){
        postsDao.deleteById(id);
        return "Post Deleted!";
    }
}
