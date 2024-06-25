package com.TMT.TMT_BE_PortFolio.mytrade.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class MemberStock {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberStockId;

    private Long amount;

    private String  stockCode;

    private Long totalAmount;

    private String stockName;

    private String uuid;

    private Long totalPrice;



}
