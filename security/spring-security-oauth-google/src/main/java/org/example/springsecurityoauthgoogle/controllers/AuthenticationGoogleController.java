package org.example.springsecurityoauthgoogle.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationGoogleController {

    @GetMapping("/auth/login/google")
    public String getLoginPage() {
        return "redirect:/oauth2/authorization/google";
    }

}
