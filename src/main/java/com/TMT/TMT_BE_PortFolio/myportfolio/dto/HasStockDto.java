package com.TMT.TMT_BE_PortFolio.myportfolio.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HasStockDto {

    private String stockName;
    private Long hasStockPrice;

}
