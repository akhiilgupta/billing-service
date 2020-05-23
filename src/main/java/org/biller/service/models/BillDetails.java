package org.biller.service.models;

import java.util.ArrayList;
import java.util.List;
import org.biller.service.enums.BillFetchStatus;
import org.biller.service.responses.BillDTO;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BillDetails {
  
  @Builder.Default
  private BillFetchStatus billFetchStatus = BillFetchStatus.NO_OUTSTANDING;
  
  @Builder.Default
  private List<BillDTO> bills = new ArrayList<>();
  
}
