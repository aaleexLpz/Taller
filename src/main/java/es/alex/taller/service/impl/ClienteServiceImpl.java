package es.alex.taller.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import es.alex.taller.dto.cliente.ClienteOutputDto;
import es.alex.taller.dto.cliente.ClienteOutputMinDto;
import es.alex.taller.dto.coche.CocheOutputMinDto;
import es.alex.taller.repository.IClienteRepo;
import es.alex.taller.repository.ICocheRepo;
import es.alex.taller.service.IClienteService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements IClienteService{
	
	private final IClienteRepo clienteRepo;
	
	private final ICocheRepo cocheRepo;
	
	@Override
	public List<ClienteOutputMinDto> listadoClientes(){
		return clienteRepo.listadoClientes();
	}

	@Override
	public ClienteOutputDto infoClienteId(Integer codCliente) {
	    // Obtener el cliente desde el repositorio
	    ClienteOutputDto clienteOutput = clienteRepo.infoClienteId(codCliente);
	    
	    // Si clienteOutput es null, inicializarlo como un nuevo objeto ClienteOutputDto
	    if (clienteOutput == null) {
	        clienteOutput = new ClienteOutputDto();
	    }
	    
	    // Obtener la lista de coches por cliente
	    List<CocheOutputMinDto> coches = cocheRepo.listadoCochesPorCliente(codCliente);
	    
	    // Establecer la lista de coches en el cliente
	    clienteOutput.setCoches(coches);
	    
	    // Devolver el cliente con la lista de coches
	    return clienteOutput;
	}



	@Override
	public void actualizarCliente(ClienteOutputDto clientes) {
		clienteRepo.actualizarCliente(clientes); 
	}

	@Override
	public Integer insertarCliente(ClienteOutputDto clientes) {
		return clienteRepo.insertarCliente(clientes);
		
	}

	@Override
	public void eliminarCliente(Integer idCliente) {
		clienteRepo.eliminarCliente(idCliente);
		
	}

	@Override
	public List<ClienteOutputDto> listadoClientesCompleto() {
		return clienteRepo.listadoClientesCompleto();
	}

}