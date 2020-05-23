package org.biller.service.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Amount {

  private Long value;

  private String currencyCode;
}
