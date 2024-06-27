package com.TMT.TMT_BE_PortFolio.myportfolio.application;

import static com.TMT.TMT_BE_PortFolio.mytrade.domain.QMemberStock.memberStock;

import com.TMT.TMT_BE_PortFolio.myportfolio.dto.HasStockDto;
import com.TMT.TMT_BE_PortFolio.myportfolio.dto.MemberStockDto;
import com.TMT.TMT_BE_PortFolio.myportfolio.infrastructure.MemberStockQueryDslImp;
import com.querydsl.core.Tuple;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


@Service
@RequiredArgsConstructor
public class MyPortFolioServiceImp implements MyPortfolioService {


    private final MemberStockQueryDslImp memberStockQueryDslImp;
    private final ReactiveRedisTemplate<String, Object> reactiveReidsTemplate;

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

        List<HasStockDto> hasStockDto = new ArrayList<>();
        for (MemberStockDto memberStockList : memberStockDto) {

            String stockCode = memberStockList.getStockCode();
            String key = "stock:" + stockCode;

            // Redis에서 주식 가격 조회
            String stockPriceStr = String.valueOf(reactiveReidsTemplate.opsForValue().get(key));

            String firstPart;
            if (stockPriceStr.contains(":")) {
                firstPart = stockPriceStr.split(":")[0];
            } else {
                firstPart = stockPriceStr;
            }

            // Long 타입으로 변환
            Long stockPrice = Long.parseLong(firstPart);

            Long hasStcokPrice = memberStockList.getTotalAmount() * stockPrice;

            HasStockDto hasStock = new HasStockDto(
                    memberStockList.getStockName(), hasStcokPrice);

        }

        sendmyStockInfo((Flux<HasStockDto>) hasStockDto);

    }

    @Override
    public Flux<HasStockDto> sendmyStockInfo(Flux<HasStockDto> hasStockDtoFlux) {
        // 예: WebClient를 사용하여 다른 서비스로 전송

    }

}
