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
		clienteService.actualizarCliente(cliente);
		ClienteOutputDto clienteAct = clienteService.infoClienteId(cliente.getId());
        model.addAttribute("clientes", clienteAct);
        return "editarCliente";
    }
	
	@PostMapping("/guardar-cliente-nuevo")
	public String guardarClienteNuevo(@ModelAttribute ClienteOutputDto cliente, Model model) {
		Integer codCliente = clienteService.insertarCliente(cliente);
		ClienteOutputDto clienteAct = clienteService.infoClienteId(codCliente);
		model.addAttribute("clientes", clienteAct);
		return "editarCliente";
	}
	
	@PostMapping("/eliminar-cliente/{id}")
	public String eliminarCliente(@PathVariable Integer id, RedirectAttributes redirectAttributes, Model model) {
		clienteService.eliminarCliente(id);
		redirectAttributes.addFlashAttribute("mensaje", "Cliente eliminado con éxito");
		List<ClienteOutputMinDto> clientes = clienteService.listadoClientes();
		model.addAttribute("clientes", clientes);
		return "redirect:/clientes-page";
	}
	
	@GetMapping("/nuevo-coche/{codCliente}")
	public String mostrarNuevoCoche(@PathVariable Integer codCliente, Model model) {
	    CocheOutputDto coche = new CocheOutputDto();
	    coche.setCodCliente(codCliente);
	    model.addAttribute("coches", coche);
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
		cocheService.actualizarCoche(coches);
		CocheOutputDto cocheAct = cocheService.infoCocheId(coches.getId());
        model.addAttribute("coches", cocheAct);
        return "editarCoche";
    }
	
	@PostMapping("/guardar-coche-nuevo")
	public String guardarCocheNuevo(@ModelAttribute CocheOutputDto coche, ClienteOutputDto cliente, Model model) {
		Integer codCoche = cocheService.insertarCoche(coche);
		CocheOutputDto cocheAct = cocheService.infoCocheId(codCoche);
        model.addAttribute("coches", cocheAct);
		model.addAttribute("clientes", cliente);
		return "editarCoche";
	}
	
	@PostMapping("/eliminar-coche/{id}")
	public String eliminarCoche(@PathVariable Integer id, RedirectAttributes redirectAttributes, Model model) {
		Integer codCliente = cocheService.eliminarCoche(id);
		redirectAttributes.addFlashAttribute("mensaje", "Coche eliminado con éxito");
		ClienteOutputDto cliente = clienteService.infoClienteId(codCliente);
		model.addAttribute("clientes", cliente);
		return "redirect:/cliente/" + cliente.getId();
	}
	
	@GetMapping("/nueva-intervencion/{id}")
	public String mostrarNuevaIntervencion(@PathVariable Integer id, Model model) {
	    IntervencionOutputDto intervencion = intervencionService.codCocheCodIntervencion(id);

	    if (intervencion == null) {
	        intervencion = new IntervencionOutputDto();
	        intervencion.setCodCoche(id);
	        model.addAttribute("intervenciones", intervencion);
	    }
	    
	    model.addAttribute("intervenciones", intervencion);
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
		Integer codIntervencion = intervencionService.insertarIntervencion(intervencion);
		IntervencionOutputDto intervencionCreada = intervencionService.infoIntervencionCodIntervencion(codIntervencion);
        model.addAttribute("intervencion", intervencionCreada);
	    return "verIntervencion";
	}

	@PostMapping("/eliminar-intervencion/{id}")
	public String eliminarIntervencion(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
	    IntervencionOutputDto intervencion = intervencionService.infoIntervencionCodIntervencion(id);
	    Integer codCoche = intervencion.getCodCoche();
	    intervencionService.eliminarIntervencion(id);
	    redirectAttributes.addFlashAttribute("mensaje", "Intervención eliminada con éxito");
	    return "redirect:/coche/" + codCoche;
	}


}