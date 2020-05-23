package org.biller.service.services;

import java.util.List;
import java.util.Optional;
import org.apache.commons.collections4.CollectionUtils;
import org.biller.service.entities.BillEntity;
import org.biller.service.entities.ReceiptEntity;
import org.biller.service.enums.BillStatus;
import org.biller.service.enums.RecurrenceType;
import org.biller.service.repos.BillsRepo;
import org.biller.service.requests.FetchReceiptRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillsRepoService {

  @Autowired
  private BillsRepo billsRepo;

  @Autowired
  private ReceiptRepoService receiptsServiceRepo;

  public List<BillEntity> findByCustomerAccountAndStatusIn(String accountId,
      List<BillStatus> billStatus) {
    return billsRepo.findByCustomerAccountAndStatusIn(accountId, billStatus);
  }

  public Optional<BillEntity> findByBillId(String billId) {
    return billsRepo.findByBillId(billId);
  }

  public boolean isValid(BillEntity billEntity, FetchReceiptRequest request) {
    List<ReceiptEntity> receiptEntities = receiptsServiceRepo.findByBillerBillId(request);

    if (RecurrenceType.ONE_TIME.equals(billEntity.getRecurrence())) {
      if (CollectionUtils.size(receiptEntities) > 0) {
        return false;
      }
      if (!billEntity.getTotalAmount()
          .equals(request.getPaymentDetails().getBillAmount().getValue())) {
        return false;
      }
      if (billEntity.getPendingAmount() < request.getPaymentDetails().getAmountPaid().getValue()) {
        return false;
      }
    }
    // exhausted list of checks. use cases not provided in the assignment

    return true;
  }

  public void updateEntity(BillEntity billEntity, FetchReceiptRequest request) {
    // logic to update the status of bill entity as well as amount. use cases not provided
    billEntity.setPendingAmount(
        billEntity.getPendingAmount() - request.getPaymentDetails().getAmountPaid().getValue());
    if (billEntity.getPendingAmount() == 0) {
      billEntity.setStatus(BillStatus.SETTLED);
    }
    billsRepo.save(billEntity);
  }


}
