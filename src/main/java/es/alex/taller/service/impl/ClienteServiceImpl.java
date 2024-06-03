package es.alex.taller.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import es.alex.taller.dto.cliente.ClienteOutputDto;
import es.alex.taller.dto.cliente.ClienteOutputMinDto;
import es.alex.taller.repository.IClienteRepo;
import es.alex.taller.service.IClienteService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements IClienteService{
	
	private final IClienteRepo clienteRepo;
	
	@Override
	public List<ClienteOutputMinDto> listadoClientes(){
		return clienteRepo.listadoClientes();
	}

	@Override
	public ClienteOutputDto infoCliente(Integer codCliente) {
		ClienteOutputDto clienteOutput = clienteRepo.infoClienteId(codCliente);
		clienteOutput.setCoches(null);
		return clienteOutput;
	}

}
