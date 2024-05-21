package es.alex.taller.controller.dtos.car;

import lombok.Data;

@Data
public class CarPatchDto {

    private String brand;

    private String model;

    private String owner;

    private String mechanic;

    private Long workshop;

    private String color;

    private String tuition;
}
