package com.TMT.TMT_BE_PortFolio.myportfolio.application;

import com.TMT.TMT_BE_PortFolio.myportfolio.dto.FeignClientResponseDto;
import com.TMT.TMT_BE_PortFolio.myportfolio.vo.HasStockResponseVo;
import com.TMT.TMT_BE_PortFolio.myportfolio.dto.MemberStockDto;
import com.TMT.TMT_BE_PortFolio.myportfolio.vo.UserStockResponseVo;
import java.util.List;

public interface MyPortfolioService {

    List<HasStockResponseVo> getMyPortFolio(String uuid);

    List<HasStockResponseVo> stockInfoRead(List<MemberStockDto> memberStockDto);

    List<UserStockResponseVo> sendNickname(String nickname);

    List<UserStockResponseVo> sendUserHasStock(FeignClientResponseDto
            feignClientResponseDto);

}
