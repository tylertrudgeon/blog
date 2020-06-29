package com.codeup.blog.controllers;

import com.codeup.blog.PostsDao.PostsRepository;
import com.codeup.blog.PostsDao.UserRepository;
import com.codeup.blog.models.Post;
import com.codeup.blog.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    private PostsRepository postsDao;
    private UserRepository userDao;

    public PostController(PostsRepository postsRepository, UserRepository userRepository) {
        postsDao = postsRepository;
        userDao = userRepository;
    }

    @GetMapping("/posts")
    public String postsIndex(Model model){
        ArrayList<Post> posts = new ArrayList<>();
//        posts.add(new Post("Second post", "Second body"));
//        posts.add(new Post("Third post", "Third body"));
        model.addAttribute("noPostsFound", posts.size() == 0);
        model.addAttribute("posts", posts);
        return "/posts/index";
    }

    @GetMapping("/posts/{id}")
    public String singlePost(@PathVariable long id, Model model){
        model.addAttribute("post", new Post("Dell PC", "Like new!", userDao.getOne(1L)));
        model.addAttribute("postId", id);
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
        Post newPost = new Post("Dell PC","Like new!", userDao.getOne(1L));
        postsDao.save(newPost);
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

    @GetMapping("/search")
    public String searchResults(Model model, @RequestParam(name = "term") String term){
        List<Post> Posts = postsDao.searchByTitle(term);
        model.addAttribute("posts", Posts);
        return "posts/index";
    }
}
