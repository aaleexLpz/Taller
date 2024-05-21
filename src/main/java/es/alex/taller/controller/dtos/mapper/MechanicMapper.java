package es.alex.taller.controller.dtos.mapper;

import es.alex.taller.controller.dtos.mechaic.MechanicCreateDto;
import es.alex.taller.controller.dtos.mechaic.MechanicPatchDto;
import es.alex.taller.controller.dtos.mechaic.MechanicReadDto;
import es.alex.taller.controller.dtos.mechaic.MechanicUpdateDto;
import es.alex.taller.model.entities.Mechanic;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MechanicMapper {

    MechanicCreateDto mechanicToCreate(Mechanic mechanic);

    Mechanic createToMechanic(MechanicCreateDto mechanicCreateDto);

    MechanicReadDto mechanicToRead(Mechanic mechanic);

    Mechanic readToMechanic(MechanicReadDto mechanicReadDto);

    MechanicUpdateDto mechanicToUpdate(Mechanic mechanic);

    Mechanic updateToMechanic(MechanicUpdateDto mechanicUpdateDto);

    MechanicPatchDto mechanicToPatch(Mechanic mechanic);

    Mechanic patchToMechanic(MechanicPatchDto mechanicPatchDto);
}
