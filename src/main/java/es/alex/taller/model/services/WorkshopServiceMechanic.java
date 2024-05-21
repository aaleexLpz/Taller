package es.alex.taller.model.services;

import es.alex.taller.model.entities.Mechanic;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;

@Service
public interface WorkshopServiceMechanic {

    public Mechanic getMechanic(String DNI) throws InstanceNotFoundException;

    public Mechanic createMechanic(Mechanic mechanic);

    public void updateMechanic(String DNI, Mechanic mechanic) throws InstanceNotFoundException;
  
    public void patchMechanic(String DNI, Mechanic mechanic) throws InstanceNotFoundException;

    public void deleteMechanic(String DNI);
}
