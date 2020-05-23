package org.biller.service.requests;

import org.biller.service.models.PaymentDetails;
import org.springframework.lang.NonNull;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_ABSENT)
public class FetchReceiptRequest {

  @NonNull
  private String billerBillID;

  @NonNull
  private String platformBillID;

  @NonNull
  private PaymentDetails paymentDetails;

}
