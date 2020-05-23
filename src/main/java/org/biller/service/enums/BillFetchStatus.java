package org.biller.service.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BillFetchStatus {

  AVAILABLE(
      new ArrayList<BillStatus>(Arrays.asList(BillStatus.CREATED, BillStatus.CREDIT_RECEIVED,
          BillStatus.SETTLEMENT_PENDING, BillStatus.WAITING_FOR_CREDIT))),
  
  NO_OUTSTANDING( new ArrayList<BillStatus>(
      Arrays.asList(BillStatus.SETTLED, BillStatus.EXPIRED, BillStatus.PAYMENT_ACK)));

  private List<BillStatus> billStatus;
}
