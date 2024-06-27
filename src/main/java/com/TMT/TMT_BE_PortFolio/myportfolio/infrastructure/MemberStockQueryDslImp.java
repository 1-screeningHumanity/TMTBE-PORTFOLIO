package com.TMT.TMT_BE_PortFolio.myportfolio.infrastructure;

import static com.TMT.TMT_BE_PortFolio.mytrade.domain.QMemberStock.memberStock;

import com.TMT.TMT_BE_PortFolio.myportfolio.infrastructure.MemberStockQueryDsl;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberStockQueryDslImp implements MemberStockQueryDsl {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Tuple> getMyStockInfo(String uuid){

        return jpaQueryFactory
                .select(memberStock.stockCode, memberStock.stockName, memberStock.totalAmount)
                .from(memberStock)
                .where(memberStock.uuid.eq(uuid).and(memberStock.totalAmount.gt(0)))
                .fetch();
    }



}
