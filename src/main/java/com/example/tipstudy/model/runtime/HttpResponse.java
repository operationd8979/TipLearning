package com.example.tipstudy.model.runtime;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class HttpResponse {

    @NonNull
    @JsonProperty("code")
    protected int code;

}
