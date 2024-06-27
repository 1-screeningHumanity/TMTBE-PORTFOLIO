package com.TMT.TMT_BE_PortFolio.myportfolio.infrastructure;

import com.TMT.TMT_BE_PortFolio.myportfolio.domain.MemberStock;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberStockRepository extends JpaRepository< MemberStock, Long> {

    Optional<MemberStock> findByUuid(String uuid);


}
