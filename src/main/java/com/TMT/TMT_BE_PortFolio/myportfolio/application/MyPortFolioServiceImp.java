package com.TMT.TMT_BE_PortFolio.myportfolio.application;

import com.TMT.TMT_BE_PortFolio.global.common.exception.CustomException;
import com.TMT.TMT_BE_PortFolio.global.common.response.BaseResponseCode;
import com.TMT.TMT_BE_PortFolio.myportfolio.dto.MemberStockDto;
import com.TMT.TMT_BE_PortFolio.mytrade.domain.MemberStock;
import com.TMT.TMT_BE_PortFolio.mytrade.infrastructure.MemberStockRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPortFolioServiceImp implements MyPortfolioService {

    private final MemberStockRepository memberStockRepository;

    @Override
    public MemberStockDto getMyPortFolio(String uuid) {

        Optional<MemberStock> memberStock = memberStockRepository.findByUuid(uuid);
        if (memberStock.isEmpty()) {
            throw new CustomException(BaseResponseCode.EMPTY_STOCK);
        }
        MemberStockDto memberStockDto = new MemberStockDto(memberStock.get().getStockCode(),
                memberStock.get().getStockName(),
                memberStock.get().getTotalAmount(),
                uuid);

        return memberStockDto;
    }



}
