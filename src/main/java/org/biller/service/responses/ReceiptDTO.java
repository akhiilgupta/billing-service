package org.biller.service.responses;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReceiptDTO {
  
  private String id;
  
  private LocalDateTime date;
}
