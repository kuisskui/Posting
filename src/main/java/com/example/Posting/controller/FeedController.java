package com.example.Posting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FeedController {
    @GetMapping("/")
    public String feed(Model model){
        model.addAttribute("greeting", "Sawaddee");
        return "feed";
    }
}
