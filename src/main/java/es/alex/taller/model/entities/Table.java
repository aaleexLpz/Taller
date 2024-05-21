package es.alex.taller.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Table {

    @Id
    private String cliente;

    private String coche;
    
    private String mecanico;
    
    private String taller;
}
