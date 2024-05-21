package es.alex.taller.controller.cars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import es.alex.taller.controller.dtos.car.CarCreateDto;
import es.alex.taller.controller.dtos.car.CarPatchDto;
import es.alex.taller.controller.dtos.car.CarReadDto;
import es.alex.taller.controller.dtos.mapper.CarMapper;
import es.alex.taller.model.entities.Car;
import es.alex.taller.model.services.WorkshopServiceCar;

import javax.management.InstanceNotFoundException;

@Controller
@RequestMapping("/car")
public class CarsController {

    @Autowired
    private WorkshopServiceCar workshopServiceCar;

    @Autowired
    private CarMapper carMapper;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Car createCar(@RequestBody CarCreateDto carCreateDto) {
            return workshopServiceCar.createCar(carCreateDto);
    }

    @GetMapping("/read/{carId}")
    public CarReadDto readCar(@PathVariable Long carId) {
        try {
            return carMapper.carToRead(workshopServiceCar.readCar(carId));
        } catch (InstanceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found", e);
        }
    }

    @PutMapping("/update/{carId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCar(@PathVariable Long carId) {
        try {
            workshopServiceCar.updateCar(carId);
        } catch (InstanceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found", e);
        }
    }

    @PatchMapping("/patch/{carId}")
    @ResponseStatus(HttpStatus.OK)
    public void patchCar(@PathVariable Long carId, @RequestBody CarPatchDto carPatchDto) {
        try {
            workshopServiceCar.patchCar(carId, carPatchDto.getBrand(), carPatchDto.getModel(),
                    carPatchDto.getOwner(), carPatchDto.getMechanic(), carPatchDto.getWorkshop(),
                    carPatchDto.getColor(), carPatchDto.getTuition());
        } catch (InstanceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found", e);
        }
    }

    @DeleteMapping("/delete/{carId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeCar(@PathVariable long carId) {
        workshopServiceCar.deleteCar(carId);
    }
}