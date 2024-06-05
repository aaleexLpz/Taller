package es.alex.taller.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.alex.taller.dto.cliente.ClienteOutputDto;
import es.alex.taller.service.IClienteService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PageController {

	private final IClienteService clienteService;
	
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
    		List<ClienteOutputDto> clientes = clienteService.listadoClientes();
    		model.addAttribute("clientes", clientes);
    		return "clientes";
    	} catch (Exception e) {
    		return null;
    	}
    }
}