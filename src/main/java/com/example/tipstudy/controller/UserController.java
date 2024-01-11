package com.example.tipstudy.controller;

import com.example.tipstudy.model.entity.AppUser;
import com.example.tipstudy.model.runtime.*;
import com.example.tipstudy.service.AppUserService;
import com.example.tipstudy.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final AppUserService appUserService;
    private final QuizService quizService;

//    @PostMapping(value = "/updateUserInfo")
//    public ResponseEntity<HttpResponse> updateUserInfo(@RequestBody UpdateInfoRequest updateRequest){
//        return ResponseEntity.ok(appUserService.updateUserInfo(updateRequest));
//    }

    @PostMapping(value = "/hello")
    public ResponseEntity<String> hello(@RequestParam String userId){
        System.out.println(userId);
        return ResponseEntity.ok("Hello");
    }

    @GetMapping(value = "/gets/{length}")
    public ResponseEntity<List<QuizResponse>> getQuizzes(@PathVariable("length") int length,@RequestParam String userId){
        AppUser user = appUserService.loadUserByUserid(userId);
        if(user==null){
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(quizService.getQuizList(length));
    }
    @PostMapping(value = "/marks")
    public ResponseEntity<MarkRecord> mark(@RequestBody MarkRecord markRecord,@RequestParam String userId){
        AppUser user = appUserService.loadUserByUserid(userId);
        if(user==null){
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(quizService.mark(markRecord));
    }

    @PostMapping(value = "/query")
    public ResponseEntity<String> query(@RequestBody RequestFindQuiz requestFindQuiz, @RequestParam String userId){
        AppUser user = appUserService.loadUserByUserid(userId);
        if(user==null){
            return ResponseEntity.status(403).build();
        }
        for(String question : requestFindQuiz.questions()){
            System.out.println(question);
        }
        return ResponseEntity.ok("ok");
    }

    @GetMapping(value = "/query/{questions}")
    public ResponseEntity<List<String>> query(@PathVariable("questions") String questions, @RequestParam String userId){
        AppUser user = appUserService.loadUserByUserid(userId);
        if(user==null){
            return ResponseEntity.status(403).build();
        }
        List<String> questionList = List.of(questions.split("\\*"));
        return ResponseEntity.ok(quizService.query(questionList));
    }

    @PostMapping(value = "/updateInfo")
    public ResponseEntity<HttpResponse> updateUserInfo(@RequestBody UpdateInfoRequest updateRequest, @RequestParam String userId){
        AppUser user = appUserService.loadUserByUserid(userId);
        if(user==null){
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(appUserService.updateUserInfo(updateRequest,userId));
    }





}
