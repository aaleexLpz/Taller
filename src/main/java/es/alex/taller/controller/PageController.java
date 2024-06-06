package es.alex.taller.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import es.alex.taller.dto.cliente.ClienteOutputDto;
import es.alex.taller.dto.cliente.ClienteOutputMinDto;
import es.alex.taller.service.IClienteService;
import es.alex.taller.service.ICocheService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PageController {

	private final IClienteService clienteService;
	private final ICocheService cocheService;
	
	@GetMapping("/quienesSomos")
	public String quienesSomos() {
		return "quienesSomos";
	}
	
	@GetMapping("/contacto")
	public String contacto() {
		return "contacto";
	}
	
	@GetMapping("/productos")
	public String productos() {
		return "productos";
	}
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/clientes-page")
    public String mostrarClientes(Model model) {
    	try {
    		List<ClienteOutputMinDto> clientes = clienteService.listadoClientes();
    		model.addAttribute("clientes", clientes);
    		return "clientes";
    	} catch (Exception e) {
    		return null;
    	}
	}
	
	@GetMapping("/nuevo-cliente")
	public String mostrarNuevoCliente() {
        return "nuevoCliente";
    }
	
	@GetMapping("/cliente/{id}")
    public String verDetallesCliente(@PathVariable Integer id, Model model) {
        ClienteOutputDto clientes = clienteService.infoClienteId(id);
        model.addAttribute("clientes", clientes);
        return "editarCliente";
    }
	
	@PostMapping("/guardar-cliente")
	public String guardarCliente(@ModelAttribute ClienteOutputDto cliente, Model model) {
		clienteService.actualizarClientes(cliente);
		ClienteOutputDto clienteAct = clienteService.infoClienteId(cliente.getId());
        model.addAttribute("clientes", clienteAct);
        return "editarCliente";
    }
	
	
	
}