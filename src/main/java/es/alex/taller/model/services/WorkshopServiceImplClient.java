package es.alex.taller.model.services;

import es.alex.taller.model.entities.Client;
import es.alex.taller.model.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.util.Optional;

@Service
public class WorkshopServiceImplClient implements WorkshopServiceClient {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client getClient(String DNI) throws InstanceNotFoundException {
        Optional<Client> client = clientRepository.findById(DNI);
        if (client.isEmpty()) throw new InstanceNotFoundException("Client not found with DNI: " + DNI);

        return client.get();
    }

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void updateClient(String DNI, Client updatedClient) throws InstanceNotFoundException {
        Optional<Client> client = clientRepository.findById(DNI);
        updatedClient.setDNI(DNI);

        if (client.isEmpty()) throw new InstanceNotFoundException("Client not found with DNI: " + DNI);

        clientRepository.save(updatedClient);
    }

    @Override
    public void patchClient(String DNI, Client patchedClient) throws InstanceNotFoundException {
        Optional<Client> optionalClient = clientRepository.findById(DNI);

        if (!optionalClient.isPresent()) {
            throw new InstanceNotFoundException("Client not found with DNI: " + DNI);
        }

        Client existingClient = optionalClient.get();

        if (patchedClient.getName() != null) {
            existingClient.setName(patchedClient.getName());
        }

        if (patchedClient.getLastName() != null) {
            existingClient.setLastName(patchedClient.getLastName());
        }

        if (patchedClient.getPhone() != null) {
            existingClient.setPhone(patchedClient.getPhone());
        }

        clientRepository.save(existingClient);
    }

    @Override
    public void deleteClient(String DNI) {
        clientRepository.deleteById(DNI);
    }
}
