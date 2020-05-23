package org.biller.service.interfaces;

import java.util.List;
import java.util.Optional;
import org.biller.service.models.AttributeIdentifiers;

public interface FindByAttributeIdentifier<T> {

  Optional<T> findByAtrributeIdentifier(AttributeIdentifiers identifiers);

  Optional<T> findByAtrributeIdentifier(List<AttributeIdentifiers> identifiers);

}
