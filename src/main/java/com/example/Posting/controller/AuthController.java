package com.example.Posting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Posting.entity.User;
import com.example.Posting.service.UserDetailsServiceImp;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
public class AuthController {
    
    @Autowired
    private UserDetailsServiceImp userDetailsService;

    @GetMapping("/login")
    public String loginView() {
        return "login";
    }

    // @PostMapping("/login")
    // public String loginFailure(@RequestParam(name = "username") String username) {
    //     try {
    //         userDetailsService.updateFailedAttempts(username);
    //     } catch (UsernameNotFoundException e) {
    //         // Username not found, do nothing
    //     }
    //     return "redirect:/login?error";
    // }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response,
                         Authentication auth) {

        if (auth != null){
            new SecurityContextLogoutHandler()
                    .logout(request, response, auth);
        }

        // You can redirect wherever you want, but generally it's a good
        // practice to show the login screen again.
        return "redirect:/login?logout";
    }
}
