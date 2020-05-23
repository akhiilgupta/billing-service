package org.biller.service.models;

import org.biller.service.enums.Attribute;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AttributeIdentifiers {

  private Attribute attributeName;
  
  private String attributeValue;
}
