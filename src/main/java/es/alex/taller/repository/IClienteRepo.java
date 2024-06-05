package es.alex.taller.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.alex.taller.dto.cliente.ClienteOutputDto;

@Repository
public interface IClienteRepo {

	List<ClienteOutputDto> listadoClientes();
	
	ClienteOutputDto infoClienteId(Integer codCliente);
	
}
