package es.alex.taller.controller.dtos.car;

import lombok.Data;

@Data
public class CarUpdateDto {

    private String brand;
    
    private String model;

    private String owner;

    private String mechanic;

    private Long workshop;

    private String color;

    private String tuition;

    public CarUpdateDto(){

    }

    public CarUpdateDto(String brand, String model, String owner, String mechanic, Long workshop, String color, String tuition){

        this.brand = brand;
        this.model = model;
        this.owner = owner;
        this.mechanic = mechanic;
        this.workshop = workshop;
        this.color = color;
        this.tuition = tuition;
    }

}
