package com.example.tipstudy.service;

import com.example.tipstudy.constant.ErrorMessages;
import com.example.tipstudy.model.entity.AppUser;
import com.example.tipstudy.model.runtime.*;
import com.example.tipstudy.repository.AppUserRepository;
import com.example.tipstudy.ulitity.DataRetrieveUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final DataRetrieveUtil dataRetrieveUtil;

    public AppUser loadUserByUserid(String userId){
        return appUserRepository.findById(userId).orElse(null);
    }

    public AppUser loadUserByEmail(String email){
        return appUserRepository.findByEmail(email).orElse(null);
    }

    public HttpResponse signUp(RequestRegister requestRegister){
        var userDetail = loadUserByEmail(requestRegister.email());
        if(userDetail!=null){
            return ErrorMessages.EXISTED_EMAIL;
        }
        AppUser user = new AppUser();
        user.setEmail(requestRegister.email());
        user.setPassword(requestRegister.password());
        user.setFullName(requestRegister.fullName());
        user.setUrlAvatar(requestRegister.urlAvatar());
        appUserRepository.save(user);
        return new AuthenticationReponse.builder().code(200)
                .userId(user.getUserId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .urlAvatar(user.getUrlAvatar())
                .build();
    }

    public HttpResponse login(RequestLogin requestLogin){
        var userDetail = loadUserByEmail(requestLogin.email());
        if(userDetail==null){
            return ErrorMessages.USERNAME_NOT_FOUND_ERROR;
        }
        if(!userDetail.getPassword().equals(requestLogin.password())){
            return ErrorMessages.ILLEGAL_PASSWORD;
        }
        return new AuthenticationReponse.builder().code(200)
                .userId(userDetail.getUserId())
                .email(userDetail.getEmail())
                .fullName(userDetail.getFullName())
                .urlAvatar(userDetail.getUrlAvatar())
                .build();
    }

    public HttpResponse updateUserInfo(UpdateInfoRequest updateRequest, String userId){
        var userDetail = loadUserByUserid(userId);
        if(userDetail==null){
            return ErrorMessages.USERNAME_NOT_FOUND_ERROR;
        }
        if(!updateRequest.password().equals(userDetail.getPassword())){
            return ErrorMessages.ILLEGAL_PASSWORD;
        }
        if(!updateRequest.fullName().equals("")){
            userDetail.setFullName(updateRequest.fullName());
        }
        if(!updateRequest.newPassword().equals("")){
            userDetail.setPassword(updateRequest.newPassword());
        }
        appUserRepository.save(userDetail);
        return new AuthenticationReponse.builder().code(200)
                .userId(userDetail.getUserId())
                .email(userDetail.getEmail())
                .fullName(userDetail.getFullName())
                .urlAvatar(userDetail.getUrlAvatar())
                .build();
    }

    public HttpResponse authGoogle(RequestAuthGoogle requestAuthGoogle){
        var userDetail = loadUserByEmail(requestAuthGoogle.email());
        if(userDetail==null){
            RequestRegister requestRegister = new RequestRegister(requestAuthGoogle.email(),
                    Base64.getEncoder().encodeToString(requestAuthGoogle.email().getBytes()),
                    requestAuthGoogle.fullName(),
                    requestAuthGoogle.urlAvatar());
            return signUp(requestRegister);
        }
        return new AuthenticationReponse.builder().code(200)
                .userId(userDetail.getUserId())
                .email(userDetail.getEmail())
                .fullName(userDetail.getFullName())
                .urlAvatar(userDetail.getUrlAvatar())
                .build();
    }





}
