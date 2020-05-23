package org.biller.service.services;

import java.util.List;
import org.biller.service.entities.ReceiptEntity;
import org.biller.service.repos.ReceiptsRepo;
import org.biller.service.requests.FetchReceiptRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiptRepoService {

  @Autowired

  private ReceiptsRepo receiptsRepo;

  public ReceiptEntity createReceipt(FetchReceiptRequest request) {
    return receiptsRepo.save(ReceiptEntity.builder().billerBillId(request.getBillerBillID())
        .platformBillId(request.getPlatformBillID())
        .transactionRefId(request.getPaymentDetails().getPlatformTransactionRefID())
        .amount(request.getPaymentDetails().getAmountPaid().getValue())
        .billAmount(request.getPaymentDetails().getBillAmount().getValue()).build());
  }

  public List<ReceiptEntity> findByBillerBillId(FetchReceiptRequest request) {
    return receiptsRepo.findByBillerBillId(request.getBillerBillID());
  }
}
