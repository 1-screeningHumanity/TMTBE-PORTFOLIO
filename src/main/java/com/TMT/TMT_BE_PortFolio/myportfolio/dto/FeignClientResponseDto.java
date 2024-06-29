package com.TMT.TMT_BE_PortFolio.myportfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FeignClientResponseDto {

    private String uuid;
    private String nickname;
    private String grade;

}
