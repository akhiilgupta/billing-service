package org.biller.service.services;

import java.util.List;
import java.util.Optional;
import org.apache.commons.collections4.CollectionUtils;
import org.biller.service.entities.CustomerEntity;
import org.biller.service.interfaces.FindByAttributeIdentifier;
import org.biller.service.models.AttributeIdentifiers;
import org.biller.service.repos.CustomersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomersRepoService implements FindByAttributeIdentifier<CustomerEntity> {

  @Autowired
  private CustomersRepo repo;

  @Override
  public Optional<CustomerEntity> findByAtrributeIdentifier(AttributeIdentifiers identifiers) {
    switch (identifiers.getAttributeName()) {
      case MOBILE_NUMBER:
        return repo.findByPhoneAndActiveTrue(identifiers.getAttributeValue());
      case EMAIL_ID:
        return repo.findByEmailAndActiveTrue(identifiers.getAttributeValue());
      case ACCOUNT_ID:
        return repo.findByAccountIdAndActiveTrue(identifiers.getAttributeValue());
      case UNKOWN:
      default:
        return Optional.empty();
    }
  }

  @Override
  public Optional<CustomerEntity> findByAtrributeIdentifier(
      List<AttributeIdentifiers> identifiers) {
    if (CollectionUtils.size(identifiers) == 1) {
      return findByAtrributeIdentifier(identifiers.get(0));
    } else if (CollectionUtils.size(identifiers) > 1) {
      // logic to find customer by multiple identifiers. Can throw non unique result if multiple
      // customers found
    }
    return Optional.empty();
  }

}
