package com.example.tipstudy.service;

import com.example.tipstudy.model.entity.Quiz;
import com.example.tipstudy.model.runtime.MarkRecord;
import com.example.tipstudy.model.runtime.QuizRecord;
import com.example.tipstudy.model.runtime.QuizResponse;
import com.example.tipstudy.repository.QuizRepository;
import com.example.tipstudy.ulitity.DataRetrieveUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final DataRetrieveUtil dataRetrieveUtil;

    public List<QuizResponse> getQuizList(int length) {
        List<Quiz> quizzes = new ArrayList<>(quizRepository.findAll());
        if(quizzes==null||quizzes.size()<length){
            return null;
        }
        Collections.shuffle(quizzes);
        return dataRetrieveUtil.translateQuizzesToResponse(quizzes.subList(0,length));
    }

    public MarkRecord mark(MarkRecord markRecord){
        int count = 0;
        int correct = 0;
        for(QuizRecord record: markRecord.getRecordList()){
            Quiz quiz = quizRepository.findById(record.getQuizId()).orElse(null);
            if(quiz!=null){
                count++;
                if(quiz.isCorrect(record.getAnswer())){
                    correct++;
                }
                else{
                    record.setAnswer(quiz.getAnswer());
                }
            }
        }
        markRecord.setScore(((float)correct*100) / count);
        return markRecord;
    }

    public List<String> query(List<String> questions){
        List<Quiz> quizzes = new ArrayList<>(quizRepository.findAll());
        List<String> listSource = quizzes.stream().map(Quiz::toString).collect(Collectors.toList());
        List<String> listResult = new ArrayList<>();
        for(String question : questions){
            String result = "";
            int maxCount = 0;
            for(String source : listSource){
                int count = 0;
                List<String> sourceList = List.of(source.split(" "));
                for(String sourceWord : sourceList){
                    if(question.contains(sourceWord)){
                        count++;
                    }
                    if(count>maxCount){
                        maxCount = count;
                        result = source;
                    }
                }
            }
            listResult.add(result);
        }
        return listResult;
    }


}
