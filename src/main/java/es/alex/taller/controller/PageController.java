package es.alex.taller.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.alex.taller.dto.cliente.ClienteOutputDto;
import es.alex.taller.dto.cliente.ClienteOutputMinDto;
import es.alex.taller.dto.coche.CocheOutputDto;
import es.alex.taller.dto.coche.CocheOutputMinDto;
import es.alex.taller.dto.intervencion.IntervencionOutputDto;
import es.alex.taller.dto.intervencion.IntervencionOutputMinDto;
import es.alex.taller.dto.intervencion.IntervencionesContainer;
import es.alex.taller.service.IClienteService;
import es.alex.taller.service.ICocheService;
import es.alex.taller.service.IIntervencionService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PageController {

	private final IClienteService clienteService;
	private final ICocheService cocheService;
	private final IIntervencionService intervencionService;
	
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
	public String mostrarNuevoCliente(Model model) {
		model.addAttribute("clientes", new ClienteOutputDto());
        return "nuevoCliente";
    }
	
	@GetMapping("/cliente/{id}")
    public String verDetallesCliente(@PathVariable Integer id, Model model) {
        ClienteOutputDto clientes = clienteService.infoClienteId(id);
        List<CocheOutputMinDto> coches = cocheService.listadoCochesPorCliente(id);
        model.addAttribute("clientes", clientes);
        model.addAttribute("coches", coches);
        return "editarCliente";
    }
	
	@PostMapping("/guardar-cliente")
	public String guardarCliente(@ModelAttribute ClienteOutputDto cliente, Model model) {
		clienteService.actualizarClientes(cliente);
		ClienteOutputDto clienteAct = clienteService.infoClienteId(cliente.getId());
        model.addAttribute("clientes", clienteAct);
        return "editarCliente";
    }
	
	@PostMapping("/guardar-cliente-nuevo")
	public String guardarClienteNuevo(@ModelAttribute ClienteOutputDto cliente, Model model) {
		clienteService.insertarClientes(cliente);
		model.addAttribute("clientes", cliente);
		return "editarCliente";
	}
	
	@GetMapping("/nuevo-coche")
	public String mostrarNuevoCoche(Model model) {
		model.addAttribute("coches", new CocheOutputDto());
        return "nuevoCoche";
    }
	
	@GetMapping("/coche/{id}")
	public String verDetallesCoche(@PathVariable Integer id, Model model) {
		CocheOutputDto coches = cocheService.infoCocheId(id);
		if (coches == null) {
	        model.addAttribute("error", "No se encontraron detalles para el coche.");
	        return "editarCliente";
	    }
		
		List<IntervencionOutputMinDto> intervenciones = intervencionService.listadoIntervencionPorCoche(id);
		model.addAttribute("coches", coches);
		
		if (intervenciones.isEmpty()) {
	        model.addAttribute("mensaje", "No hay intervenciones para este coche.");
	    } else {
	        model.addAttribute("intervenciones", intervenciones);
	    }
		return "editarCoche";
	}
	
	@PostMapping("/guardar-coche")
	public String guardarCoche(@ModelAttribute CocheOutputDto coches, Model model) {
		cocheService.actualizarCoches(coches);
		CocheOutputDto cocheAct = cocheService.infoCocheId(coches.getId());
        model.addAttribute("coches", cocheAct);
        return "editarCoche";
    }
	
	@PostMapping("/guardar-coche-nuevo")
	public String guardarCocheNuevo(@ModelAttribute CocheOutputDto coche, ClienteOutputDto cliente, Model model) {
		cocheService.insertarCoches(coche);
		model.addAttribute("coches", coche);
		model.addAttribute("clientes", cliente);
		return "editarCoche";
	}
	
	@GetMapping("/nueva-intervencion")
	public String mostrarNuevaIntervencion(Model model) {
		model.addAttribute("intervenciones", new IntervencionOutputDto());
		return "nuevaIntervencion";
	}
	
	@GetMapping("/intervencion/{id}")
	public String verDetallesIntervencion(@PathVariable Integer id, Model model) {
	    IntervencionOutputDto intervencion = intervencionService.infoIntervencionCodIntervencion(id);
        model.addAttribute("intervencion", intervencion);
	    return "verIntervencion";
	}

	@PostMapping("/guardar-intervencion-nueva")
	public String guardarIntervencionNueva(@ModelAttribute IntervencionOutputDto intervencion, ClienteOutputDto cliente, Model model) {
		Integer codIntervencion = intervencionService.insertarIntervenciones(intervencion);
		IntervencionOutputDto intervencionCreada = intervencionService.infoIntervencionCodIntervencion(codIntervencion);
        model.addAttribute("intervencion", intervencionCreada);
	    return "verIntervencion";
	}

	
}