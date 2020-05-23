package org.biller.service.repos;

import java.util.Optional;
import org.biller.service.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepo extends JpaRepository<CustomerEntity, Long> {

  Optional<CustomerEntity> findByPhoneAndActiveTrue(String phone);

  Optional<CustomerEntity> findByEmailAndActiveTrue(String email);

  Optional<CustomerEntity> findByAccountIdAndActiveTrue(String accountId);

}

