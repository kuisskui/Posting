package com.example.Posting.controller;

import com.example.Posting.repository.PostRepository;
import com.example.Posting.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FeedController {

    @Autowired
    private FeedService feedService;

    @GetMapping("/")
    public String feed(Model model){
        model.addAttribute("greeting", "Sawaddee");
        model.addAttribute("postDTOs", feedService.getPostDTOs());
        return "feed";
    }
}
