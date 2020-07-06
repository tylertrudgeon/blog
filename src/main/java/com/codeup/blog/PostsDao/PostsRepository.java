package com.codeup.blog.PostsDao;

import com.codeup.blog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostsRepository extends JpaRepository<Post, Long> {

    @Query("from Post as p where p.title like %:term% or p.body like %:term%")
    List<Post> searchByTitle(@Param("term") String term);

    Post findByTitle(String title);
}
