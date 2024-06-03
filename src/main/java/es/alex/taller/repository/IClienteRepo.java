package es.alex.taller.repository;

import java.util.List;

import es.alex.taller.dto.cliente.ClienteOutputDto;
import es.alex.taller.dto.cliente.ClienteOutputMinDto;

public interface IClienteRepo {

	List<ClienteOutputMinDto> listadoClientes();
	
	ClienteOutputDto infoClienteId(Integer codCliente);
	
}
