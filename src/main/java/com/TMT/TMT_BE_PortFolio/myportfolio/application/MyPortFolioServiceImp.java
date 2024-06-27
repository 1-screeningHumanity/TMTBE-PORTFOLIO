package com.TMT.TMT_BE_PortFolio.myportfolio.application;

import static com.TMT.TMT_BE_PortFolio.myportfolio.domain.QMemberStock.memberStock;
import com.TMT.TMT_BE_PortFolio.myportfolio.vo.HasStockResponseVo;
import com.TMT.TMT_BE_PortFolio.myportfolio.dto.MemberStockDto;
import com.TMT.TMT_BE_PortFolio.myportfolio.infrastructure.MemberStockQueryDslImp;
import com.querydsl.core.Tuple;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class MyPortFolioServiceImp implements MyPortfolioService {


    private final MemberStockQueryDslImp memberStockQueryDslImp;
//    private final ReactiveRedisTemplate<String, Object> reactiveRedisTemplate;
    private final RedisTemplate<String, Object> redisTemplate;



    @Override //MemberStock Read
    public List<HasStockResponseVo> getMyPortFolio(String uuid) {
        List<Tuple> tupleList = memberStockQueryDslImp.getMyStockInfo(uuid);
        List<MemberStockDto> memberStockDto = tupleList.stream().map(this::maptoDto).toList();
        return stockInfoRead(memberStockDto);
    }

    private MemberStockDto maptoDto (Tuple tuple) { //tuple to dto

        Long totalAmount  = tuple.get(memberStock.totalAmount);
        String uuid = tuple.get(memberStock.uuid);
        String stockCode = tuple.get(memberStock.stockCode);
        String stockName = tuple.get(memberStock.stockName);
        return new MemberStockDto(stockCode, stockName, totalAmount, uuid);

    }

    @Override
    public List<HasStockResponseVo> stockInfoRead(List<MemberStockDto> memberStockDto) {

        List<HasStockResponseVo> hasStockDto = new ArrayList<>();
        for (MemberStockDto memberStockList : memberStockDto) {

            String stockCode = memberStockList.getStockCode();
            String key = "stock:" + stockCode;

            // Redis에서 주식 가격 조회
            String stockPriceStr = String.valueOf(redisTemplate.opsForValue().get(key));
            String firstPart;
            if (stockPriceStr.contains(":")) {
                firstPart = stockPriceStr.split(":")[0];
            } else {
                firstPart = stockPriceStr; //이게 null로 담긴다.
            }
            // Long 타입으로 변환
            Long stockPrice = Long.parseLong(firstPart);
            Long hasStcokPrice = memberStockList.getTotalAmount() * stockPrice;
            HasStockResponseVo hasStock = new HasStockResponseVo(memberStockList.getStockName(),
                    hasStcokPrice);
            hasStockDto.add(hasStock);

        }
            return hasStockDto;
    }

}
