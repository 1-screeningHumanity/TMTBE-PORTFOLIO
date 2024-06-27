package com.TMT.TMT_BE_PortFolio.myportfolio.application;

import com.TMT.TMT_BE_PortFolio.myportfolio.vo.HasStockResponseVo;
import com.TMT.TMT_BE_PortFolio.myportfolio.dto.MemberStockDto;
import java.util.List;

public interface MyPortfolioService {

    //MemberStock Read


    //MemberStock Read
    List<HasStockResponseVo> getMyPortFolio(String uuid);

    List<HasStockResponseVo> stockInfoRead(List<MemberStockDto> memberStockDto);

}
