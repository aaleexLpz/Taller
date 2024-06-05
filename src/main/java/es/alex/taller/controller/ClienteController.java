package es.alex.taller.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.alex.taller.dto.cliente.ClienteOutputDto;
import es.alex.taller.dto.cliente.ClienteOutputMinDto;
import es.alex.taller.service.IClienteService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ClienteController {

    private final IClienteService clienteService;
    
    @GetMapping("/api/clientes")
    public ResponseEntity<List<ClienteOutputDto>> listadoClientes() {
        try {
            List<ClienteOutputDto> clientes = clienteService.listadoClientes();
            return ResponseEntity.ok(clientes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    
    @GetMapping("/api/clientes/info")
    public ResponseEntity<ClienteOutputDto> infoCliente(@RequestParam("codCliente") Integer codCliente) {
    	ResponseEntity<ClienteOutputDto> response;
        try {
            response = ResponseEntity.ok(clienteService.infoClienteId(codCliente));
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return response;
    }

//    @GetMapping("/clientes-page")
//    public String mostrarClientes(Model model) {
//    	try {
//    		List<ClienteOutputMinDto> clientes = clienteService.listadoClientes();
//    		model.addAttribute("clientes", clientes);
//    		return "clientes";
//    	} catch (Exception e) {
//    		return null;
//    	}
//    }
    
}