package org.biller.service.responses;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CustomerDTO {
  
  private String name;
  
  private String id;
}
