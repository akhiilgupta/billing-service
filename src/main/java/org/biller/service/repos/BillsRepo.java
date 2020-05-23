package org.biller.service.repos;

import java.util.List;
import java.util.Optional;
import org.biller.service.entities.BillEntity;
import org.biller.service.enums.BillStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillsRepo extends JpaRepository<BillEntity, Long> {

  List<BillEntity> findByCustomerAccountAndStatusIn(String customerAccount,
      List<BillStatus> status);

  Optional<BillEntity> findByBillId(String billId);
}
