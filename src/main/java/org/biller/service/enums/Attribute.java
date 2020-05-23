package org.biller.service.enums;

import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Attribute {

  MOBILE_NUMBER("mobileNumber"),
  BILL_ID("billerBillId"),
  EMAIL_ID("emailId"),
  ACCOUNT_ID("accountId"),
  UNKOWN("unkonwn");

  @JsonCreator
  public static Attribute setAttribute(String attribute) {
    return Arrays.stream(Attribute.values()).filter(s -> s.getValue().equalsIgnoreCase(attribute))
        .findFirst().orElse(UNKOWN);
  }

  private String value;
}
