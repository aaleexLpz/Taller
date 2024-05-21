package es.alex.taller.controller.dtos.client;

import lombok.Data;

@Data
public class ClientUpdateDto {

    private String name;

    private String lastName;

    private Long phone;

    private Long car;
}
