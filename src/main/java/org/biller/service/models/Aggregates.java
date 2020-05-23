package org.biller.service.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Aggregates {
  
  private Long total;
  
  private Long pending;
}
