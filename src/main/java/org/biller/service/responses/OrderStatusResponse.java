package org.biller.service.responses;

import org.biller.service.enums.BillStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class OrderStatusResponse {
  
  private BillStatus status;
  
  private String message;
}
