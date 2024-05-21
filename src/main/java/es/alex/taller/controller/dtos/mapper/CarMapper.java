package es.alex.taller.controller.dtos.mapper;

import es.alex.taller.controller.dtos.car.CarCreateDto;
import es.alex.taller.controller.dtos.car.CarPatchDto;
import es.alex.taller.controller.dtos.car.CarReadDto;
import es.alex.taller.controller.dtos.car.CarUpdateDto;
import es.alex.taller.model.entities.Car;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {

    CarCreateDto carToCarCreateDto(Car car);

    Car createDtoToCar(CarCreateDto carCreateDto);

    CarReadDto carToRead(Car car);

    Car readDtoToCar(CarReadDto carReadDto);

    CarUpdateDto carToUpdate(Car car);

    Car updateDtoToCar(CarUpdateDto carUpdateDto);

    CarPatchDto carToPatch(Car car);

    Car patchDtoToCar(CarPatchDto carPatchDto);
}
