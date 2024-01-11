package com.example.tipstudy.model.runtime;

import java.util.List;

public record QuizResponse(String quizId, String title, List<String> answers) {

}
