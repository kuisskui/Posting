package com.example.Posting.controller;

import com.example.Posting.dto.CommentRequest;
import com.example.Posting.dto.PostDTO;
import com.example.Posting.repository.PostRepository;
import com.example.Posting.service.FeedService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FeedController {

    @Autowired
    private FeedService feedService;

    @GetMapping("/")
    public String feed(Model model){
        model.addAttribute("greeting", "Sawaddee");
        model.addAttribute("postDTOs", feedService.findPostDTOs());
        return "feed";
    }

    @GetMapping("/comments/{postId}")
    public String getComment(Integer postId, Model model){
        // TODO send comment form and show the post and comments

        model.addAttribute("commentRequest", new CommentRequest());
        model.addAttribute("post", feedService.findPostDTOิById(postId));
        return "comment";
    }

    @PostMapping("/comments")
    public String createComment(@Valid CommentRequest comment, BindingResult result, Model model){
        // TODO save comment
        return "redirect:/";
    }
}
