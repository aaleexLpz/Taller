package es.alex.taller.model.services;

import java.util.Optional;

import javax.management.InstanceNotFoundException;

import org.springframework.stereotype.Service;

import es.alex.taller.controller.dtos.car.CarCreateDto;
import es.alex.taller.model.entities.Car;
import es.alex.taller.model.repository.CarRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkshopServiceImplCar implements WorkshopServiceCar {

    private final CarRepository carRepository;

    @Override
    public Car createCar(CarCreateDto carCreateDto) {
        Car car = new Car();
        car.setModel(carCreateDto.getModel());
        car.setBrand(carCreateDto.getBrand());
        car.setOwner(carCreateDto.getOwner());
        car.setColor(carCreateDto.getColor());
        car.setWorkshop(carCreateDto.getWorkshop());
        car.setTuition(carCreateDto.getTuition());
        car.setMechanic(carCreateDto.getMechanic());

        return carRepository.save(car);
    }

    @Override
    public Car readCar(Long carId) throws InstanceNotFoundException {
        Optional<Car> optionalCar = carRepository.findById(carId);

        if (optionalCar.isEmpty()) {
            throw new InstanceNotFoundException("Car not found with ID: " + carId);
        }

        return optionalCar.get();
    }

    @Override
    public void updateCar(Long carId) throws InstanceNotFoundException {
        Optional<Car> optionalCar = carRepository.findById(carId);

        if (optionalCar.isEmpty()) {
            throw new InstanceNotFoundException("Car not found with ID: " + carId);
        }

        Car newCar = optionalCar.get();

        carRepository.save(newCar);
    }

    @Override
    public void patchCar(Long carId, String brand, String model, String owner, String mechanic, Long workshop,
                         String color, String tuition) throws InstanceNotFoundException {
        Optional<Car> optionalCar = carRepository.findById(carId);

        if (optionalCar.isEmpty()) {
            throw new InstanceNotFoundException("Car not found with ID: " + carId);
        }

        Car existingCar = optionalCar.get();

        if (brand != null) {
            existingCar.setBrand(brand);
        }

        if (model != null) {
            existingCar.setModel(model);
        }

        if (owner != null) {
            existingCar.setOwner(owner);
        }

        if (mechanic != null) {
            existingCar.setMechanic(mechanic);
        }

        if (workshop != null) {
            existingCar.setWorkshop(workshop);
        }

        if (color != null) {
            existingCar.setColor(color);
        }

        if (tuition != null) {
            existingCar.setTuition(tuition);
        }

        carRepository.save(existingCar);
    }

    @Override
    public void deleteCar (Long carId) {
        carRepository.deleteById(carId);
    }
}