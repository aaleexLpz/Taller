package es.alex.taller.model.services;

import es.alex.taller.model.entities.Mechanic;
import es.alex.taller.model.repository.MechanicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.util.Optional;

@Service
public class WorkshopServiceImplMechanic implements WorkshopServiceMechanic {

    @Autowired
    MechanicRepository mechanicRepository;

    @Override
    public Mechanic getMechanic(String DNI) throws InstanceNotFoundException {
        Optional<Mechanic> mechanic = mechanicRepository.findById(DNI);
        if (mechanic.isEmpty()) throw new InstanceNotFoundException("Mechanic not found with DNI: " + DNI);

        return mechanic.get();
    }

    @Override
    public Mechanic createMechanic(Mechanic mechanic) {
        return mechanicRepository.save(mechanic);
    }

    @Override
    public void updateMechanic(String DNI, Mechanic updatedMechanic) throws InstanceNotFoundException {
        Optional<Mechanic> mechanic = mechanicRepository.findById(DNI);
        updatedMechanic.setDNI(DNI);

        if (mechanic.isEmpty()) throw new InstanceNotFoundException("Mechanic not found with DNI: " + DNI);

        mechanicRepository.save(updatedMechanic);
    }

    @Override
    public void patchMechanic(String DNI, Mechanic patchedMechanic) throws InstanceNotFoundException {
        Optional<Mechanic> optionalMechanic = mechanicRepository.findById(DNI);

        if (!optionalMechanic.isPresent()) {
            throw new InstanceNotFoundException("Mechanic not found with DNI: " + DNI);
        }

        Mechanic existingMechanic = optionalMechanic.get();

        if (patchedMechanic.getName() != null) {
            existingMechanic.setName(patchedMechanic.getName());
        }

        if (patchedMechanic.getPhone() != null) {
            existingMechanic.setPhone(patchedMechanic.getPhone());
        }

        if (patchedMechanic.getWorkshop() != null) {
            existingMechanic.setWorkshop(patchedMechanic.getWorkshop());
        }

        if (patchedMechanic.getCar() != null) {
            existingMechanic.setCar(patchedMechanic.getCar());
        }

        mechanicRepository.save(existingMechanic);
    }

    @Override
    public void deleteMechanic(String DNI) {
        mechanicRepository.deleteById(DNI);
    }
}
