package es.alex.taller.controller.clients;

import es.alex.taller.controller.dtos.client.*;
import es.alex.taller.controller.dtos.mapper.ClientMapper;
import es.alex.taller.model.services.WorkshopServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.management.InstanceNotFoundException;

@Controller
@RequestMapping("/client")
public class ClientsController {

    @Autowired
    private WorkshopServiceClient workshopServiceClient;

    @Autowired
    private ClientMapper clientMapper;

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
