package com.example.letsquiz.controllers;


import com.example.letsquiz.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/app_user")
@CrossOrigin
public class AppUserController {

    private final AppUserService service;


    @GetMapping("/isLoginExist/{username}")
    public boolean isUserExist(@PathVariable String username){
        return service.existsByUsername(username);
    }

    @GetMapping("isEmailExist/{email}")
    public boolean isEmailExist(@PathVariable String email){
        return service.existsByEmail(email);
    }
}
