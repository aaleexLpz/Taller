package es.alex.taller.controller.dtos.mapper;

import es.alex.taller.controller.dtos.client.ClientCreateDto;
import es.alex.taller.controller.dtos.client.ClientPatchDto;
import es.alex.taller.controller.dtos.client.ClientReadDto;
import es.alex.taller.controller.dtos.client.ClientUpdateDto;
import es.alex.taller.model.entities.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client clientToRead(ClientReadDto clientReadDto);

    ClientReadDto readToClient (Client client);

    Client createToClient(ClientCreateDto clientCreateDto);

    ClientReadDto clientToCreate(Client client);

    Client updateToClient(ClientUpdateDto clientUpdateDto);

    ClientUpdateDto clientToUpdate(Client client);

    Client patchToClient(ClientPatchDto clientPatchDto);

    ClientPatchDto clientToPatch(Client client);
}
