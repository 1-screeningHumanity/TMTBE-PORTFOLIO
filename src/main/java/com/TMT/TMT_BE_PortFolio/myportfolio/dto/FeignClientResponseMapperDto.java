package com.TMT.TMT_BE_PortFolio.myportfolio.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FeignClientResponseMapperDto {

    @JsonProperty("httpStatus")
    private String httpStatus;

    @JsonProperty("isSuccess")
    private boolean isSuccess;

    @JsonProperty("message")
    private String message;

    @JsonProperty("code")
    private int code;

    @JsonProperty("data")
    private FeignClientResponseDto data;

}