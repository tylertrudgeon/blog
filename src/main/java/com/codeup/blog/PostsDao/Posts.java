package com.codeup.blog.PostsDao;

import com.codeup.blog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Posts extends JpaRepository<Post, Long> {

}
