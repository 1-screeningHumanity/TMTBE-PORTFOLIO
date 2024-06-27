package com.TMT.TMT_BE_PortFolio.myportfolio.application;

import com.TMT.TMT_BE_PortFolio.myportfolio.dto.HasStockDto;
import com.TMT.TMT_BE_PortFolio.myportfolio.dto.MemberStockDto;
import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MyPortfolioService {


    //MemberStock Read
    void getMyPortFolio(String uuid);

    void stockInfoRead(List<MemberStockDto> memberStockDto);

    Mono<Void> sendmyStockInfo(Flux<HasStockDto> hasStockDtoFlux);
}
