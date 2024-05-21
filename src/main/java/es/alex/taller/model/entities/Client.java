package es.alex.taller.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Client {

    @Id
    private String DNI;

    private String name;

    private String lastName;

    private Long phone;

    private Long car;

}