package org.biller.service.repos;

import org.biller.service.entities.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestsRepo extends JpaRepository<RequestEntity, Long>{
  
  boolean existsByUniqueId(String uniqueId);
}
