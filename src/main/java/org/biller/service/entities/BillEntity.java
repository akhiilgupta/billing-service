package org.biller.service.entities;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.biller.service.enums.BillStatus;
import org.biller.service.enums.PaymentType;
import org.biller.service.enums.RecurrenceType;
import org.biller.service.utils.JSONBUserType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.json.simple.JSONObject;
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
@Table(name = "bills")
@TypeDef(name = "JSONBUserType", typeClass = JSONBUserType.class)
public class BillEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NonNull
  private String billId;

  @NonNull
  @Enumerated(EnumType.STRING)
  private BillStatus status;

  @NonNull
  private String customerAccount;

  @NonNull
  private String billType;

  @Builder.Default
  @Enumerated(EnumType.STRING)
  private RecurrenceType recurrence = RecurrenceType.ONE_TIME;

  @NonNull
  @Enumerated(EnumType.STRING)
  private PaymentType amountExactness;

  @NonNull
  private Long pendingAmount;

  @NonNull
  private Long totalAmount;

  @Column(insertable = false, updatable = false)
  private LocalDateTime createdAt;

  @NonNull
  private LocalDateTime dueDate;

  @Type(type = "JSONBUserType")
  private JSONObject metadata;

}
