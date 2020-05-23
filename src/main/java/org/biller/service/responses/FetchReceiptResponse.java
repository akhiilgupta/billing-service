package org.biller.service.responses;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FetchReceiptResponse {
  
  private String billerBillID;
  
  private String platformBillID;
  
  private String platformTransactionRefID;
  
  private ReceiptDTO receipt;
  
}
