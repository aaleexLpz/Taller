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
	public List<ClienteOutputDto> listadoClientes(){
		return clienteRepo.listadoClientes();
	}

	@Override
	public ClienteOutputDto infoClienteId(Integer codCliente) {
		ClienteOutputDto clienteOutput = clienteRepo.infoClienteId(codCliente);
		List<CocheOutputMinDto> coches = cocheRepo.listadoCochesPorCliente(codCliente);
		clienteOutput.setCoches(coches);
		return clienteOutput;
	}

}