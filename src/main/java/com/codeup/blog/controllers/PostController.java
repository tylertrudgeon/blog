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
    public String createPostForm(Model postCreateModel){
        postCreateModel.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("posts/create")
    public String createPost(@ModelAttribute Post postToBeSaved){
        User currentUser = userDao.getOne(1L);
        postToBeSaved.setOwner(currentUser);
        Post newPost = postsDao.save(postToBeSaved);
        return "redirect:/posts/" + newPost.getID();
    }

    @GetMapping("/posts/{id}/edit")
    public String showEditForm(Model model, @PathVariable Long id) {
        Post postToEdit = postsDao.getOne(id);
        model.addAttribute("post", postToEdit);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    @ResponseBody
    public String editPost(@ModelAttribute Post postToEdit) {
        User currentUser = userDao.getOne(1L);
        postToEdit.setOwner(currentUser);
        postsDao.save(postToEdit);
        return "redirect:/ads/" + postToEdit.getID();
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
