package com.TMT.TMT_BE_PortFolio.myportfolio.application;

import com.TMT.TMT_BE_PortFolio.myportfolio.dto.MemberStockDto;
import com.TMT.TMT_BE_PortFolio.myportfolio.vo.MyPortFolioResponseVo;
import java.util.List;
import reactor.core.publisher.Mono;

public interface MyPortfolioService {

    void getMyPortFolio(String uuid);

    void stockInfoRead(List<MemberStockDto> memberStockDto);

}
