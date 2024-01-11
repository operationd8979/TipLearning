package com.example.tipstudy.repository;

import com.example.tipstudy.model.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface QuizRepository extends JpaRepository<Quiz, String> {

    @Override
    Optional<Quiz> findById(String quizId);
}
