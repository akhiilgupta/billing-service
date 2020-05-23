package org.biller.service.responses;

import org.biller.service.models.BillDetails;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FetchCustomerBillsResponse {

  private CustomerDTO customer;

  @Builder.Default
  private BillDetails billDetails = BillDetails.builder().build();
}
