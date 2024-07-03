package com.TMT.TMT_BE_PortFolio.myportfolio.application;

import static com.TMT.TMT_BE_PortFolio.myportfolio.domain.QMemberStock.memberStock;

import com.TMT.TMT_BE_PortFolio.global.common.exception.CustomException;
import com.TMT.TMT_BE_PortFolio.global.common.response.BaseResponseCode;
import com.TMT.TMT_BE_PortFolio.myportfolio.dto.FeignClientResponseDto;
import com.TMT.TMT_BE_PortFolio.myportfolio.dto.FeignClientResponseMapperDto;
import com.TMT.TMT_BE_PortFolio.myportfolio.vo.HasStockResponseVo;
import com.TMT.TMT_BE_PortFolio.myportfolio.dto.MemberStockDto;
import com.TMT.TMT_BE_PortFolio.myportfolio.infrastructure.MemberStockQueryDslImp;
import com.TMT.TMT_BE_PortFolio.myportfolio.vo.UserStockResponseVo;
import com.querydsl.core.Tuple;
import java.math.BigDecimal;
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
    private final RedisTemplate<String, Object> redisTemplate;
    private final SendNickname sendNickname;



    @Override //MemberStock Read
    public List<HasStockResponseVo> getMyPortFolio(String uuid) {
        List<Tuple> tupleList = memberStockQueryDslImp.getStockInfo(uuid);
        List<MemberStockDto> memberStockDto = tupleList.stream().map(this::maptoDto).toList();
        if (memberStockDto.isEmpty()) {
            throw new CustomException(BaseResponseCode.EMPTY_STOCK);
        }
        return stockInfoRead(memberStockDto);
    }

    private MemberStockDto maptoDto (Tuple tuple) { //tuple to dto

        Long totalAmount  = tuple.get(memberStock.totalAmount);
        String uuid = tuple.get(memberStock.uuid);
        String stockCode = tuple.get(memberStock.stockCode);
        String stockName = tuple.get(memberStock.stockName);
        Long totalPrice = tuple.get(memberStock.totalPrice);

        return new MemberStockDto(stockCode, stockName, totalAmount, uuid, totalPrice);

    }

    @Override //내가 보유중인 주식정보 조회
    public List<HasStockResponseVo> stockInfoRead(List<MemberStockDto> memberStockDto) {

        List<HasStockResponseVo> hasStockDto = new ArrayList<>();
        for (MemberStockDto memberStockList : memberStockDto) {

            String stockCode = memberStockList.getStockCode();
            Long totalAmount = memberStockList.getTotalAmount();
            String stockName = memberStockList.getStockName();
            Long totalPrice = memberStockList.getTotalPrice();
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

            BigDecimal amountBD = BigDecimal.valueOf(totalAmount);
            BigDecimal priceBD = BigDecimal.valueOf(totalPrice);
            BigDecimal averagePrice = priceBD.divide(amountBD, 10, BigDecimal.ROUND_HALF_UP);

            HasStockResponseVo hasStock = new HasStockResponseVo(stockName, totalAmount, stockPrice, averagePrice);
            hasStockDto.add(hasStock);

        }
            return hasStockDto;
    }

    @Override//페인 클라이언트를 이용해 Member 정보 들고옴
    public List<UserStockResponseVo> sendNickname(String nickname){

        FeignClientResponseMapperDto feignClientResponseMapperDto = sendNickname.sendNickname(nickname);
        FeignClientResponseDto feignClientResponseDto = feignClientResponseMapperDto.getData();
        log.info("feignClientResponseDtouuid ={} ",feignClientResponseDto.getNickname());
        log.info("feignClientResponseDtoNickname ={} ",feignClientResponseDto.getNickname());
        return sendUserHasStock(feignClientResponseDto);
    }

    @Override //다른회원 보유중인 주식 조회
    public List<UserStockResponseVo> sendUserHasStock(FeignClientResponseDto
            feignClientResponseDto) {

        String uuid = feignClientResponseDto.getUuid();
        log.info("uuid ={} ",uuid);
        List<Tuple> tupleList = memberStockQueryDslImp.getStockInfo(uuid);
        List<MemberStockDto> memberStockDto = tupleList.stream().map(this::maptoDto).toList();
        String nickName = feignClientResponseDto.getNickname();
        String grade = feignClientResponseDto.getGrade();

        if (memberStockDto.isEmpty()) {
            UserStockResponseVo hasStock = new UserStockResponseVo(nickName, grade,
                    null, null, null ,null);
            List<UserStockResponseVo> userHasStock = new ArrayList<>();
            userHasStock.add(hasStock);
            return userHasStock;
        }

        List<UserStockResponseVo> userHasStock = new ArrayList<>();
        for (MemberStockDto memberStockList : memberStockDto) {

            String stockCode = memberStockList.getStockCode();
            String key = "stock:" + stockCode;
            Long totalAmount = memberStockList.getTotalAmount();
            String stockName = memberStockList.getStockName();
            Long totalPrice = memberStockList.getTotalPrice();

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

            BigDecimal amountBD = BigDecimal.valueOf(totalAmount);
            BigDecimal priceBD = BigDecimal.valueOf(totalPrice);
            BigDecimal averagePrice = priceBD.divide(amountBD, 10, BigDecimal.ROUND_HALF_UP);

            UserStockResponseVo hasStock = new UserStockResponseVo(nickName, grade,
                    stockName, totalAmount, stockPrice, averagePrice);
            userHasStock.add(hasStock);

        }
        return userHasStock;

    }

}
