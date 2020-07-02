package com.codeup.blog.controllers;

import com.codeup.blog.PostsDao.PostsRepository;
import com.codeup.blog.PostsDao.UserRepository;
import com.codeup.blog.models.Post;
import com.codeup.blog.models.User;
import com.codeup.blog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class PostController {
    private PostsRepository postsDao;
    private UserRepository userDao;
    private final EmailService emailService;


    public PostController(PostsRepository postsRepository, UserRepository userRepository, EmailService emailService) {
        this.postsDao = postsRepository;
        this.userDao = userRepository;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String postsIndex(Model model){
        List<Post> posts = postsDao.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("noPostsFound", posts.size() == 0);
        return "/posts/index";
    }

    @GetMapping("/posts/{id}")
    public String singlePost(@PathVariable long id, Model model){
        Post post = postsDao.getOne(id);
        model.addAttribute("postId", id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("posts/create")
    public String createPostForm(Model postCreateModel){
        postCreateModel.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("posts/create")
    public String createPost(@ModelAttribute Post postToBeSaved){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        postToBeSaved.setOwner(currentUser);
        Post newPost = postsDao.save(postToBeSaved);
        emailService.prepareAndSend(newPost, "A new post has been created","A new post has been created with the id of: " + newPost.getID() + " with a title of: " + newPost.getTitle());
        return "redirect:/posts/" + newPost.getID();
    }

    @GetMapping("/posts/{id}/edit")
    public String showEditForm(Model model, @PathVariable Long id) {
        Post postToEdit = postsDao.getOne(id);
        model.addAttribute("post", postToEdit);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String editPost(@ModelAttribute Post postToEdit) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        postToEdit.setOwner(currentUser);
        postsDao.save(postToEdit);
        return "redirect:/posts/" + postToEdit.getID();
    }

    @PostMapping("/posts/{id}/delete")
    public String delete(@PathVariable long id){
        postsDao.deleteById(id);
        return "redirect:/posts/";
    }

    @GetMapping("/search")
    public String searchResults(Model model, @RequestParam(name = "term") String term){
        List<Post> Posts = postsDao.searchByTitle(term);
        model.addAttribute("posts", Posts);
        return "posts/index";
    }
}
