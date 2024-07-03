package com.TMT.TMT_BE_PortFolio.myportfolio.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class MemberStockDto {

    private String stockCode;
    private String stockName;
    private Long totalAmount;
    private String uuid;
    private Long totalPrice;

}
