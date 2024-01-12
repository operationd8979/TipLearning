package com.example.tipstudy.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String quizId;
    private String title;
    private String answer;
    private long countReport = 0;
    @Enumerated(EnumType.STRING)
    private QuizType typeQuiz;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<Bait> baits;

    public Quiz(){

    }

    public Quiz(String title,String answer){
        this.title = title;
        this.answer = answer;
    }

    public boolean addBait(String bait){
        if(baits.stream().anyMatch(b->b.getBaitBody().equals(bait))){
            return false;
        }
        return true;
    }

    public boolean removeBait(String bait){
        if(baits.stream().anyMatch(b->b.getBaitBody().equals(bait))){
            return true;
        }
        return false;
    }

    public boolean isCorrect(String answer){
        return this.answer.equals(answer);
    }

    public List<String> getBaits() {
        return baits.stream().map(b->b.getBaitBody()).toList();
    }

    @Override
    public String toString() {
        String quizString = "";
        quizString += title+" [ "+answer+" ] ";
        for(Bait bait: baits){
            quizString += bait.getBaitBody()+" ";
        }
        return quizString;
    }

}