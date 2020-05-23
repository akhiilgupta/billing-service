package org.biller.service.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.biller.service.entities.BillEntity;
import org.biller.service.entities.CustomerEntity;
import org.biller.service.entities.ReceiptEntity;
import org.biller.service.enums.BillFetchStatus;
import org.biller.service.models.Aggregates;
import org.biller.service.models.BillDetails;
import org.biller.service.requests.FetchCustomerBillsRequest;
import org.biller.service.requests.FetchReceiptRequest;
import org.biller.service.responses.BillDTO;
import org.biller.service.responses.CustomerDTO;
import org.biller.service.responses.FetchCustomerBillsResponse;
import org.biller.service.responses.FetchReceiptResponse;
import org.biller.service.responses.ReceiptDTO;
import org.biller.service.responses.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BillingService {

  @Autowired
  private CustomersRepoService customersRepoService;

  @Autowired
  private BillsRepoService billsServiceRepo;

  @Autowired
  private ReceiptRepoService receiptRepoService;

  /**
   * Method to fetch bills and generate receipts and update bill balance. Store the receipts log in
   * the db.
   * 
   * @param request {@link FetchReceiptRequest}
   * @return return {@link WrapperResponse<FetchReceiptResponse>}
   */

  public WrapperResponse<FetchReceiptResponse> fetchReceipt(FetchReceiptRequest request) {
    Optional<BillEntity> billOptional = billsServiceRepo.findByBillId(request.getBillerBillID());
    if (billOptional.isPresent()) {

      if (billsServiceRepo.isValid(billOptional.get(), request)) {
        billsServiceRepo.updateEntity(billOptional.get(), request);
        ReceiptEntity receiptEntity = receiptRepoService.createReceipt(request);

        return WrapperResponse.<FetchReceiptResponse>builder()
            .data(FetchReceiptResponse.builder().billerBillID(receiptEntity.getBillerBillId())
                .platformBillID(receiptEntity.getPlatformBillId())
                .platformTransactionRefID(receiptEntity.getTransactionRefId())
                .receipt(ReceiptDTO.builder().date(receiptEntity.getCreatedAt())
                    .id(receiptEntity.getReceiptId()).build())
                .build())
            .build();
      }
      return WrapperResponse.<FetchReceiptResponse>builder().status(404)
          .message("invalid-request for the bill: " + billOptional.get().getBillId()).build();
    }

    return WrapperResponse.<FetchReceiptResponse>builder().status(404).message("bill-not-found")
        .build();
  }

  /**
   * Method to fetch bills from customer identifier for a given a user.
   * 
   * @param request {@link FetchCustomerBillsRequest}
   * @return {@link WrapperResponse<FetchCustomerBillsResponse>}
   */

  public WrapperResponse<FetchCustomerBillsResponse> fetchCustomerBills(
      FetchCustomerBillsRequest request) {

    Optional<CustomerEntity> entityOptional =
        customersRepoService.findByAtrributeIdentifier(request.getCustomerIdentifiers());
    if (entityOptional.isPresent()) {
      FetchCustomerBillsResponse response = FetchCustomerBillsResponse.builder().build();
      CustomerEntity entity = entityOptional.get();

      // If customer is present in the database
      List<BillEntity> bills = billsServiceRepo.findByCustomerAccountAndStatusIn(
          entity.getAccountId(), request.getStatus().getBillStatus());
      response.setCustomer(
          CustomerDTO.builder().name(entity.getName()).id(entity.getAccountId()).build());

      if (!CollectionUtils.isEmpty(bills)) {
        // if outstanding bills present. Map bills to bill Dto
        List<BillDTO> billDTOs =
            bills.stream().map(bill -> mapBillToDTO(bill, entity)).collect(Collectors.toList());
        response.setBillDetails(BillDetails.builder().billFetchStatus(BillFetchStatus.AVAILABLE)
            .bills(billDTOs).build());
      }

      // Create FetchCustomerBillsResponse response and wrap it with wrapper
      return WrapperResponse.<FetchCustomerBillsResponse>builder().data(response).build();
    }
    return WrapperResponse.<FetchCustomerBillsResponse>builder().status(404)
        .message("customer-not-found").build();
  }

  private BillDTO mapBillToDTO(BillEntity bill, CustomerEntity entity) {
    // logic to calculate aggregates of the amount

    return BillDTO.builder().amountExactness(bill.getAmountExactness())
        .billerBillID(bill.getBillId()).generatedOn(bill.getCreatedAt())
        .billStatus(bill.getStatus()).recurrence(bill.getRecurrence())
        .aggregates(Aggregates.builder().total(bill.getTotalAmount())
            .pending(bill.getPendingAmount()).build())
        .customerAccount(
            CustomerDTO.builder().name(entity.getName()).id(entity.getAccountId()).build())
        .build();
  }

}
