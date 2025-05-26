package com.multithread.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.multithread.model.*;

@Repository
public interface EntityRepository extends JpaRepository<EntityModel, Long> {
  // Puoi aggiungere query personalizzate se vuoi
}
