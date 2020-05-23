package org.biller.service.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDetails {

  private String platformTransactionRefID;
  
  private String uniquePaymentRefID;
  
  private Amount amountPaid;
  
  private Amount billAmount;
  
}
