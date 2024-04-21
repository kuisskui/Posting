package com.example.Posting.controller;

import com.example.Posting.dto.PostRequest;
import com.example.Posting.entity.Post;
import com.example.Posting.dto.CommentRequest;
import com.example.Posting.service.CommentService;
import com.example.Posting.service.FeedService;
import com.example.Posting.service.PostService;
import com.example.Posting.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public String feed(Model model){
        model.addAttribute("postDTOs", feedService.findPostDTOs());
        model.addAttribute("postRequest", new PostRequest());
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
            model.addAttribute("postDTOs", feedService.findPostDTOs());
            return "feed";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer userId = userService.findByUsername(authentication.getName()).getId();

            postService.createPost(new Post(userId, post.getTitle(), post.getContent()));

        model.addAttribute("postSuccess", true);

        return "redirect:/";
    }

    @GetMapping("/comments/{postId}")
    public String getComment(@PathVariable Integer postId, Model model){
        model.addAttribute("commentRequest", new CommentRequest());
        model.addAttribute("postDTO", feedService.findPostDTOิById(postId));
        model.addAttribute("commentDTOs", feedService.findCommentDTOsByPostId(postId));
        return "comment";
    }

    @PostMapping("/comments/{postId}")
    public String createComment(@PathVariable Integer postId, @Valid CommentRequest commentRequest, BindingResult result, Model model){
        if (result.hasErrors()){
            List<String> errorMessages = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            model.addAttribute("commentError", errorMessages);
            return "redirect:/comments/" + postId;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer userId = userService.findByUsername(authentication.getName()).getId();
        commentService.save(commentRequest, userId, postId);

        return "redirect:/comments/" + postId;
    }

}
