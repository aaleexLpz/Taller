package es.alex.taller.controller.mechanics;

import es.alex.taller.controller.dtos.mapper.MechanicMapper;
import es.alex.taller.controller.dtos.mechaic.*;
import es.alex.taller.model.services.WorkshopServiceMechanic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.management.InstanceNotFoundException;

@Controller
@RequestMapping("/mechanic")
public class MechanicsController {

    @Autowired
    private MechanicMapper mechanicMapper;

    @Autowired
    private WorkshopServiceMechanic workshopServiceMechanic;

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
