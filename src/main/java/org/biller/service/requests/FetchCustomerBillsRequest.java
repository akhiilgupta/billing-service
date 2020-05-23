package org.biller.service.requests;

import java.util.List;
import org.biller.service.enums.BillFetchStatus;
import org.biller.service.models.AttributeIdentifiers;
import org.springframework.lang.NonNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FetchCustomerBillsRequest {

  @NonNull
  private List<AttributeIdentifiers> customerIdentifiers;

  private BillFetchStatus status = BillFetchStatus.AVAILABLE;

}
