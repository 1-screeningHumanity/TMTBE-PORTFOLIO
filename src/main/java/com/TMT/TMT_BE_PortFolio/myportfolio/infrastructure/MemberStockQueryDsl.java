package com.TMT.TMT_BE_PortFolio.myportfolio.infrastructure;

import com.querydsl.core.Tuple;
import java.util.List;

public interface MemberStockQueryDsl {

    List<Tuple> getMyStockInfo(String uuid);

}
