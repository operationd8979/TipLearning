package com.example.tipstudy.model.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(generator = "custom-id", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "custom-id", strategy = "com.example.tipstudy.ulitity.UserIdGenerator")
    private String userId;
    private String email;
    private String password;
    private String fullName;
    private String urlAvatar;

    public AppUser(String email, String password, String fullName, String urlAvatar) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.urlAvatar = urlAvatar;
    }


}
