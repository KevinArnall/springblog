package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repos.PostRepository;
import com.codeup.springblog.repos.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping("/posts")
    public String posts(Model model) {

        Post post = new Post("Title", "Description", userDao.findById(1L).orElse(null));
        postDao.save(post);

        model.addAttribute("posts", postDao.findAll());

        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String postsById(@PathVariable long id, Model model) {

        model.addAttribute("post", postDao.findById(id).orElse(null));

        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String createPost() {
        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    public String postPost() {
        return "create a new post";
    }
}
