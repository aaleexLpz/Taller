package es.alex.taller.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.alex.taller.dto.cliente.ClienteOutputDto;
import es.alex.taller.dto.cliente.ClienteOutputMinDto;
import es.alex.taller.dto.coche.CocheOutputMinDto;
import es.alex.taller.dto.intervencion.IntervencionOutputMinDto;

@Repository
public interface IClienteRepo {

	List<ClienteOutputMinDto> listadoClientes();
	
	List<ClienteOutputDto> listadoClientesCompleto();
	
	ClienteOutputDto infoClienteId(Integer codCliente);
	
	public int actualizarCliente(ClienteOutputDto clientes);
	
	Integer insertarCliente(ClienteOutputDto cliente);
	
	void eliminarCliente(Integer idCliente);
	
}