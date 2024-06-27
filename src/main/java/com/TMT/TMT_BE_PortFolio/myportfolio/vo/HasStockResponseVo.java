package com.TMT.TMT_BE_PortFolio.myportfolio.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HasStockResponseVo {

    private String stockName;
    private Long hasStockPrice;

}
