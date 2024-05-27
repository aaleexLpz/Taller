package es.alex.taller.controller.clients;

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

import es.alex.taller.controller.dtos.client.ClientCreateDto;
import es.alex.taller.controller.dtos.client.ClientPatchDto;
import es.alex.taller.controller.dtos.client.ClientReadDto;
import es.alex.taller.controller.dtos.client.ClientUpdateDto;
import es.alex.taller.controller.dtos.mapper.ClientMapper;
import es.alex.taller.model.services.WorkshopServiceClient;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientsController {

    private final WorkshopServiceClient workshopServiceClient;

    private final ClientMapper clientMapper;

    @GetMapping("/read/{dni}")
    public ClientReadDto readClient(@PathVariable String dni) {
        try {
            return clientMapper.readToClient(workshopServiceClient.getClient(dni));
        } catch (InstanceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found", e);
        }
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createClient(@RequestBody ClientCreateDto clientCreateDto, @PathVariable String dni) {
        workshopServiceClient.createClient(clientMapper.createToClient(clientCreateDto));
    }

    @PutMapping("/update/{dni}")
    @ResponseStatus(HttpStatus.OK)
    public void updateClient(@PathVariable String dni, @RequestBody ClientUpdateDto clientUpdateDto) {
        try {
            workshopServiceClient.updateClient(dni, clientMapper.updateToClient(clientUpdateDto));
        } catch (InstanceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found", e);
        }
    }

    @PatchMapping("/patch/{dni}")
    @ResponseStatus(HttpStatus.OK)
    public void patchClient(@PathVariable String dni, @RequestBody ClientPatchDto clientPatchDto) {
        try {
            workshopServiceClient.patchClient(dni, clientMapper.patchToClient(clientPatchDto));
        } catch (InstanceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found", e);
        }
    }

    @DeleteMapping("/delete/{dni}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable String dni) {
        workshopServiceClient.deleteClient(dni);
    }
}
