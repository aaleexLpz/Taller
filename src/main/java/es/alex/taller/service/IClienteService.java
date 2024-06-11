package es.alex.taller.service;

import java.util.List;

import es.alex.taller.dto.cliente.ClienteOutputDto;
import es.alex.taller.dto.cliente.ClienteOutputMinDto;

public interface IClienteService {

	List<ClienteOutputMinDto> listadoClientes();
	
	List<ClienteOutputDto> listadoClientesCompleto();
	
	ClienteOutputDto infoClienteId(Integer codCliente);
	
	void actualizarCliente(ClienteOutputDto clientes);
	
	Integer insertarCliente(ClienteOutputDto clientes);
	
	void eliminarCliente(Integer idCliente);
}