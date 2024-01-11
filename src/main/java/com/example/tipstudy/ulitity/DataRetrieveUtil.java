package com.example.tipstudy.ulitity;

import com.example.tipstudy.model.entity.AppUser;
import com.example.tipstudy.model.entity.Quiz;
import com.example.tipstudy.model.runtime.QuizResponse;
import com.example.tipstudy.model.runtime.TinyUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Component
@AllArgsConstructor
public class DataRetrieveUtil {

    public TinyUser TranslateAppUserToTiny(AppUser appUser) {
        return new TinyUser(appUser.getUserId(), appUser.getEmail(), appUser.getFullName(), appUser.getUrlAvatar());
    }

    public QuizResponse translateQuizToTiny(Quiz quiz){
        List<String> answers = new ArrayList<>();
        List<String> baits = new ArrayList<>(quiz.getBaits());
        Collections.shuffle(baits);
        answers.addAll(baits.subList(0,3));
        answers.add(quiz.getAnswer());
        Collections.shuffle(answers);
        return new QuizResponse(quiz.getQuizId(),quiz.getTitle(),answers);
    }

    public List<QuizResponse> translateQuizzesToResponse(List<Quiz> quizList){
        List<QuizResponse> quizResponseList = new ArrayList<>();
        for(Quiz q:quizList){
            quizResponseList.add(translateQuizToTiny(q));
        }
        return quizResponseList;
    }



}
