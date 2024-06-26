package com.TMT.TMT_BE_PortFolio.myportfolio.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyPortFolioResponseVo {

    private String stockName;
    private Long totalAmount;
    private Long totalPrice;

}
