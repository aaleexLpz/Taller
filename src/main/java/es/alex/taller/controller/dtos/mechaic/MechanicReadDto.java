package es.alex.taller.controller.dtos.mechaic;

import lombok.Data;

@Data
public class MechanicReadDto {

    private String Dni;

    private String name;

    private String lastName;

    private Long phone;

    private Long workshop;

    private Long car;
}