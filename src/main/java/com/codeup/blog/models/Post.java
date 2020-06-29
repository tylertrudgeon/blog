package com.codeup.blog.models;

import javax.persistence.*;

@Entity
@Table(name ="posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false)
    private String body;

    @OneToOne
    private User owner;

    public Post(){}

    public Post(String title, String body, User user) {
        this.title = title;
        this.body = body;
        this.owner = user;
    }

    public Post(Long id, String title, String body, User user) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.owner = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getID() { return id; }

    public void setId(long id) { this.id = id; }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
