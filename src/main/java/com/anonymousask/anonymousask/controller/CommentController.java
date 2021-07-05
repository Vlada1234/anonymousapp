package com.anonymousask.anonymousask.controller;

import com.anonymousask.anonymousask.model.Comment;
import com.anonymousask.anonymousask.model.Post;
import com.anonymousask.anonymousask.service.CommentService;
import com.anonymousask.anonymousask.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
@SessionAttributes("comment")
public class CommentController {

    private final PostService postService;

    private final CommentService commentService;

    @Autowired
    public CommentController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("/comment/{id}")
    public String showComment(@PathVariable Long id, Model model) {

        Optional<Post> optionalPost = this.postService.getById(id);

        if(optionalPost.isPresent()) {
            Comment comment = new Comment();
            comment.setPost(optionalPost.get());
            model.addAttribute("comment", comment);
            return "commentForm";
        } else {
            return "404";
        }
    }

    @PostMapping("/comment")
    public String validateComment(@Valid @ModelAttribute Comment comment) {
        this.commentService.save(comment);
        return  "redirect:/post/" + comment.getPost().getId();
    }

}
