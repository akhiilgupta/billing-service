package org.biller.service.entities;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "receipts")
public class ReceiptEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  @Builder.Default
  private String receiptId = RandomStringUtils.randomAlphanumeric(10);

  @NonNull
  private String billerBillId;

  @NonNull
  private String platformBillId;

  @NonNull
  private String transactionRefId;
  
  @NonNull
  private Long amount;
  
  @NonNull
  private Long billAmount;

  @Column(insertable = false, updatable = false)
  private LocalDateTime createdAt;
}
