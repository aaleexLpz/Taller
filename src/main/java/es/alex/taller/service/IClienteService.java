package es.alex.taller.service;

import java.util.List;

import es.alex.taller.dto.cliente.ClienteOutputDto;
import es.alex.taller.dto.cliente.ClienteOutputMinDto;

public interface IClienteService {

	List<ClienteOutputDto> listadoClientes();
	
	ClienteOutputDto infoClienteId(Integer codCliente);
	
}