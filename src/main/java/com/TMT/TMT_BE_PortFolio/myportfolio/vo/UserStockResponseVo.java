package com.TMT.TMT_BE_PortFolio.myportfolio.vo;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserStockResponseVo {

    private String nickname;
    private String grade;
    private String stockName;
    private Long totalAmount;
    private Long stockPrice;
    private BigDecimal averagePrice;
}
