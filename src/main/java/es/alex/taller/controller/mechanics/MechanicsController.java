package es.alex.taller.controller.mechanics;

import javax.management.InstanceNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import es.alex.taller.controller.dtos.mapper.MechanicMapper;
import es.alex.taller.controller.dtos.mechaic.MechanicCreateDto;
import es.alex.taller.controller.dtos.mechaic.MechanicPatchDto;
import es.alex.taller.controller.dtos.mechaic.MechanicReadDto;
import es.alex.taller.controller.dtos.mechaic.MechanicUpdateDto;
import es.alex.taller.model.services.WorkshopServiceMechanic;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mechanic")
public class MechanicsController {

    private final MechanicMapper mechanicMapper;

    private final WorkshopServiceMechanic workshopServiceMechanic;

    @GetMapping("/read/{dni}")
    public MechanicReadDto getMechanic(@PathVariable String dni) {
        try {
            return mechanicMapper.mechanicToRead(workshopServiceMechanic.getMechanic(dni));
        } catch (InstanceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mechanic not found", e);
        }
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createMechanic(@RequestBody MechanicCreateDto mechanicCreateDto) {
        workshopServiceMechanic.createMechanic(mechanicMapper.createToMechanic(mechanicCreateDto));
    }

    @PutMapping("/update/{dni}")
    @ResponseStatus(HttpStatus.OK)
    public void updateMechanic(@PathVariable String dni, @RequestBody MechanicUpdateDto mechanicUpdateDto) {
        try {
            workshopServiceMechanic.updateMechanic(dni, mechanicMapper.updateToMechanic(mechanicUpdateDto));
        } catch (InstanceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mechanic not found", e);
        }
    }

    @PatchMapping("/patch/{dni}")
    @ResponseStatus(HttpStatus.OK)
    public void patchMechanic(@PathVariable String dni, @RequestBody MechanicPatchDto mechanicPatchDto) {
        try {
            workshopServiceMechanic.patchMechanic(dni, mechanicMapper.patchToMechanic(mechanicPatchDto));
        } catch (InstanceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mechanic not found", e);
        }
    }

    @DeleteMapping("/delete/{dni}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMechanic(@PathVariable String dni) {
        workshopServiceMechanic.deleteMechanic(dni);
    }
}
