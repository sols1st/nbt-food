package com.solsist.server.controller;

import com.solsist.server.util.annotion.ScopeRequired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class ProtectedController {

    @GetMapping("/")
    public String protectedRoot() {
        return "Protected root.";
    }

    @ScopeRequired("admin")
    @GetMapping("/secret")
    public String protectedSecret() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "Protected secret.";
    }

    @GetMapping("/profile")
    public String publicProfile() {
        return "Public profile.";
    }
}