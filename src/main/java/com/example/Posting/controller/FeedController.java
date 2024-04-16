package com.example.Posting.controller;

import com.example.Posting.config.SecurityConfig;
import com.example.Posting.dto.CommentRequest;
import com.example.Posting.dto.PostDTO;
import com.example.Posting.entity.Comment;
import com.example.Posting.repository.PostRepository;
import com.example.Posting.service.CommentService;
import com.example.Posting.service.FeedService;
import com.example.Posting.service.UserService;
import com.nimbusds.jose.proc.SecurityContext;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FeedController {

    @Autowired
    private FeedService feedService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public String feed(Model model){
        model.addAttribute("greeting", "Sawaddee");
        model.addAttribute("postDTOs", feedService.findPostDTOs());
        return "feed";
    }

    @GetMapping("/comments/{postId}")
    public String getComment(@PathVariable Integer postId, Model model){
        model.addAttribute("commentRequest", new CommentRequest());
        model.addAttribute("postDTO", feedService.findPostDTOิById(postId));
        return "comment";
    }

    @PostMapping("/comments/{postId}")
    public String createComment(@PathVariable Integer postId, @Valid CommentRequest commentRequest, BindingResult result, Model model){
        if (result.hasErrors()){
            List<String> errorMessages = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            model.addAttribute("postError", errorMessages);
            return "redirect:/comments/" + postId;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Integer userId = userService.findByUsername(authentication.getName()).getId();
        commentService.save(commentRequest, userId, postId);

        // TODO save comment
        return "redirect:/";
    }
}
