package com.example.tipstudy.model.runtime;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;


public class AuthenticationReponse extends HttpResponse {

    @JsonProperty("user")
    private TinyUser user;


    public AuthenticationReponse(int code){
        super(code);
    }

    public AuthenticationReponse(@NonNull int code, TinyUser user){
        super(code);
        this.user = user;
    }

    public AuthenticationReponse(builder builder){
        super(builder.code);
        this.user = builder.user;
    }

    public static class builder{
        private int code;
        private TinyUser user;
        public builder code(int code){
            this.code = code;
            return this;
        }
        public builder userId(String userId){
            if(this.user==null){
                this.user = new TinyUser();
            }
            this.user.setUserId(userId);
            return this;
        }
        public builder email(String email){
            if(this.user==null){
                this.user = new TinyUser();
            }
            this.user.setEmail(email);
            return this;
        }
        public builder fullName(String fullName){
            if(this.user==null){
                this.user = new TinyUser();
            }
            this.user.setFullName(fullName);
            return this;
        }
        public builder urlAvatar(String urlAvatar){
            if(this.user==null){
                this.user = new TinyUser();
            }
            this.user.setUrlAvatar(urlAvatar);
            return this;
        }
        public builder tinyUser(TinyUser user){
            this.user = user;
            return this;
        }
        public AuthenticationReponse build(){
            return new AuthenticationReponse(this);
        }
    }


}
