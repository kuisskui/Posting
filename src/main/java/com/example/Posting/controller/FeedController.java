package com.example.Posting.controller;

import com.example.Posting.dto.PostRequest;
import com.example.Posting.dto.SignupRequest;
import com.example.Posting.entity.Post;
import com.example.Posting.repository.PostRepository;
import com.example.Posting.service.FeedService;
import com.example.Posting.service.PostService;
import com.example.Posting.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FeedController {

    @Autowired
    private FeedService feedService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String feed(Model model){
        model.addAttribute("greeting", "Sawaddee");
        model.addAttribute("postDTOs", feedService.getPostDTOs());
        return "feed";
    }

    @GetMapping("/posts")
    public String getPost(Model model){
        model.addAttribute("postRequest", new PostRequest());
        return "post";
    }

    @PostMapping("/posts")
    public String createPost(@Valid PostRequest post, BindingResult result, Model model){
        if (result.hasErrors()){
            List<String> errorMessages = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            model.addAttribute("postError", errorMessages);
            return "post";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer userId = userService.findByUsername(authentication.getName()).getId();

            postService.createPost(new Post(userId, post.getTitle(), post.getContent()));

        model.addAttribute("postSuccess", true);

        return "redirect:/";
    }

}
