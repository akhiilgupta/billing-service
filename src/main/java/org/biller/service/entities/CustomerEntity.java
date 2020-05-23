package org.biller.service.entities;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Table(name = "customers")
@TypeDef(name = "JsonDataUserType", typeClass = JSONBUserType.class)
public class CustomerEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NonNull
  private String name;

  private String email;

  private String displayName;

  @NonNull
  private String phone;

  @NonNull
  private String accountId;

  @Type(type = "JsonDataUserType")
  @Builder.Default
  private JSONObject metadata = new JSONObject();

  private boolean active;

  @Column(insertable = false, updatable = false)
  private LocalDateTime createdAt;
}
