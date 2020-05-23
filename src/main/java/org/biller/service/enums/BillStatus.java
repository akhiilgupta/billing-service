package org.biller.service.enums;

public enum BillStatus {
  CREATED,
  WAITING_FOR_CREDIT,
  CREDIT_RECEIVED,
  SETTLEMENT_PENDING,
  SETTLED,
  EXPIRED,
  PAYMENT_ACK;
}
