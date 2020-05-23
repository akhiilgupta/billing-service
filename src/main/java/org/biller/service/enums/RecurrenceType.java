package org.biller.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RecurrenceType {
  ONE_TIME(1),
  INSTALLMENTS(5);
  
  private int maxInstallments;
}
