package com.example.tipstudy.controller;

import com.example.tipstudy.model.runtime.*;
import com.example.tipstudy.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AppUserService appUserService;

    @PostMapping("/register")
    public ResponseEntity<HttpResponse> signUp(@RequestBody RequestRegister requestRegister){
        return ResponseEntity.ok(appUserService.signUp(requestRegister));
    }

    @PostMapping("/login")
    public ResponseEntity<HttpResponse> login(@RequestBody RequestLogin requestLogin){
        return ResponseEntity.ok(appUserService.login(requestLogin));
    }

    @PostMapping("/authGoogle")
    public ResponseEntity<HttpResponse> authGoogle(@RequestBody RequestAuthGoogle requestAuthGoogle){
        return ResponseEntity.ok(appUserService.authGoogle(requestAuthGoogle));
    }




}
