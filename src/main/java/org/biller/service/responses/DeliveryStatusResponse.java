package org.biller.service.responses;

import org.biller.service.enums.BillStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryStatusResponse {
  
  private String message;
  
  @JsonProperty("status")
  private BillStatus status;
}
