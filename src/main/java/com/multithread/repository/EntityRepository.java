package com.multithread.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.multithread.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable; 

@Repository
public interface EntityRepository extends JpaRepository<EntityModel, Long> {
  Page<EntityModel> findAll(Pageable pageable);
}
