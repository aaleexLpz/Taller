package es.alex.taller.controller.home;

import es.alex.taller.model.repository.CarRepository;
import es.alex.taller.model.repository.ClientRepository;
import es.alex.taller.model.repository.MechanicRepository;
import es.alex.taller.model.repository.WorkshopRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class HomeController {

    private final CarRepository carRepository;
    private final ClientRepository clientRepository;
    private final MechanicRepository mechanicRepository;
    private final WorkshopRepository workshopRepository;

    public HomeController(
            CarRepository carRepository,
            ClientRepository clientRepository,
            MechanicRepository mechanicRepository,
            WorkshopRepository workshopRepository) {
        this.carRepository = carRepository;
        this.clientRepository = clientRepository;
        this.mechanicRepository = mechanicRepository;
        this.workshopRepository = workshopRepository;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegister() {
        return "register";
    }
}  