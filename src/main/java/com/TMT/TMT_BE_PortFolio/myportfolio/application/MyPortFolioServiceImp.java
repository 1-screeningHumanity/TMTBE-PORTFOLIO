package com.TMT.TMT_BE_PortFolio.myportfolio.application;

import static com.TMT.TMT_BE_PortFolio.mytrade.domain.QMemberStock.memberStock;

import com.TMT.TMT_BE_PortFolio.myportfolio.dto.MemberStockDto;
import com.TMT.TMT_BE_PortFolio.mytrade.infrastructure.MemberStockQueryDslImp;
import com.TMT.TMT_BE_PortFolio.mytrade.infrastructure.MemberStockRepository;
import com.querydsl.core.Tuple;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPortFolioServiceImp implements MyPortfolioService {

    private final MemberStockRepository memberStockRepository;
    private final MemberStockQueryDslImp memberStockQueryDslImp;

    @Override //MemberStock Read
    public void getMyPortFolio(String uuid) {

        List<Tuple> tupleList = memberStockQueryDslImp.getMyStockInfo(uuid);
        List<MemberStockDto> memberStockDto = tupleList.stream().map(this::maptoDto).toList();
        stockInfoRead(memberStockDto);
    }

    private MemberStockDto maptoDto (Tuple tuple) { //tuple to dto

        Long totalAmount  = tuple.get(memberStock.totalAmount);
        String uuid = tuple.get(memberStock.uuid);
        String stockCode = tuple.get(memberStock.stockCode);
        String stockName = tuple.get(memberStock.stockName);
        return new MemberStockDto(stockCode, stockName, totalAmount, uuid);

    }


    @Override
    public void stockInfoRead(List<MemberStockDto> memberStockDto){





    }



}
