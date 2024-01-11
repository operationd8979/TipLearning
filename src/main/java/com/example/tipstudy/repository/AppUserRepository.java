package com.example.tipstudy.repository;

import com.example.tipstudy.model.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<AppUser, String> {

    @Override
    Optional<AppUser> findById(String userId);
    Optional<AppUser> findByEmail(String email);
}
