package org.biller.service.responses;

import java.time.LocalDateTime;
import org.biller.service.enums.BillStatus;
import org.biller.service.enums.PaymentType;
import org.biller.service.enums.RecurrenceType;
import org.biller.service.models.Aggregates;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class BillDTO {

  private String billerBillID;

  private LocalDateTime generatedOn;

  private BillStatus billStatus;

  private RecurrenceType recurrence;

  private PaymentType amountExactness;

  private CustomerDTO customerAccount;

  private Aggregates aggregates;

}
