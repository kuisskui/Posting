package com.example.Posting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TermsOfServiceController {

    @GetMapping("/terms")
    public String terms() {
        return "terms";
    }
}
