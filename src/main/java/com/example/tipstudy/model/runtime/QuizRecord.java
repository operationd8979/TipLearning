package com.example.tipstudy.model.runtime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuizRecord {
    private String quizId;
    private String answer;
}
