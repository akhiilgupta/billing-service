package org.biller.service.controllers;

import org.biller.service.requests.FetchCustomerBillsRequest;
import org.biller.service.requests.FetchReceiptRequest;
import org.biller.service.responses.FetchCustomerBillsResponse;
import org.biller.service.responses.FetchReceiptResponse;
import org.biller.service.responses.WrapperResponse;
import org.biller.service.services.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/bills")
public class BillingController {

  @Autowired
  private BillingService billingService;

  @PostMapping("/fetch")
  public WrapperResponse<FetchCustomerBillsResponse> fetchCustomerBills(
      @Validated @RequestBody FetchCustomerBillsRequest request) {
    return billingService.fetchCustomerBills(request);
  }

  @PostMapping("/fetchReceipt")
  public WrapperResponse<FetchReceiptResponse> fetchReceipt(
      @Validated @RequestBody FetchReceiptRequest request) {
    return billingService.fetchReceipt(request);
  }
}
