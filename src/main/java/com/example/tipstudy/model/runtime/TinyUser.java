package com.example.tipstudy.model.runtime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TinyUser {
    private String userId;
    private String email;
    private String fullName;
    private String urlAvatar;
}
