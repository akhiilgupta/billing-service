package org.biller.service.repos;

import java.util.List;
import org.biller.service.entities.ReceiptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptsRepo extends JpaRepository<ReceiptEntity, Long> {

  List<ReceiptEntity> findByBillerBillId(String billerBillID);

}
