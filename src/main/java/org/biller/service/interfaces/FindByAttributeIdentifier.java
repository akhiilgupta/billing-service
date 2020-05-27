package org.biller.service.interfaces;

import java.util.List;
import java.util.Optional;
import org.apache.commons.collections4.CollectionUtils;
import org.biller.service.models.AttributeIdentifiers;

public interface FindByAttributeIdentifier<T> {

  Optional<T> findByAtrributeIdentifier(AttributeIdentifiers identifiers);

  default Optional<T> findByAtrributeIdentifier(List<AttributeIdentifiers> identifiers) {
    if (CollectionUtils.size(identifiers) == 1) {
      return findByAtrributeIdentifier(identifiers.get(0));
    } else if (CollectionUtils.size(identifiers) > 1) {
      // logic to find customer by multiple identifiers. Can throw non unique result if multiple
      // customers found
    }
    return Optional.empty();
  }

  Optional<T> findByMultipleAtrributeIdentifier(List<AttributeIdentifiers> identifiers);

}
