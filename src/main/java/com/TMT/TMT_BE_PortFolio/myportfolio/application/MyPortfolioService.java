package com.TMT.TMT_BE_PortFolio.myportfolio.application;

import com.TMT.TMT_BE_PortFolio.myportfolio.dto.MemberStockDto;
import java.util.Optional;

public interface MyPortfolioService {

    MemberStockDto getMyPortFolio(String uuid);
}
