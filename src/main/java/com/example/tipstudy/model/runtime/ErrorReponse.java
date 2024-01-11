package com.example.tipstudy.model.runtime;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;

public class ErrorReponse extends HttpResponse{

    @JsonProperty("errorMessage")
    private String errorMessage;

    public ErrorReponse(@NonNull int code, String message){
        super(code);
        this.errorMessage = message;
    }

    public ErrorReponse(builder builder){
        super(builder.code);
        this.errorMessage = builder.errorMessage;
    }

    public static class builder{
        private int code;
        private String errorMessage;
        public builder code(int code){
            this.code = code;
            return this;
        }
        public builder errorMessage(String errorMessage){
            this.errorMessage = errorMessage;
            return this;
        }
        public ErrorReponse build(){
            return new ErrorReponse(this);
        }
    }

}