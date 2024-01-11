package com.example.tipstudy.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bait {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String baitId;
    private String baitBody;

    @ManyToOne
    @JoinColumn(name = "quizId")
    private Quiz quiz;
}
