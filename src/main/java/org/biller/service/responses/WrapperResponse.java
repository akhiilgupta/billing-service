package org.biller.service.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class WrapperResponse<T> {

  private static final String SUCCESS = "SUCCESS";

  private T data;

  @JsonProperty("status")
  @Builder.Default
  private int status = 200;

  @Builder.Default
  private String message = SUCCESS;

  @JsonProperty("success")
  public Boolean isSuccess() {
    return this.status == 200;
  }
}
