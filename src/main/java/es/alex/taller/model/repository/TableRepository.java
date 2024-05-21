package es.alex.taller.model.repository;

import es.alex.taller.model.entities.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<Table, String>{
    
}
