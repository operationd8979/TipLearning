package com.example.tipstudy.model.runtime;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MarkRecord {

    private List<QuizRecord> recordList;
    private float score;

}