package es.alex.taller.repository.impl;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;

import es.alex.taller.dto.cliente.ClienteOutputDto;
import es.alex.taller.dto.cliente.ClienteOutputMinDto;
import es.alex.taller.repository.IClienteRepo;
import lombok.RequiredArgsConstructor;


@Repository
@RequiredArgsConstructor
public class ClienteRepoImpl implements IClienteRepo { 

	private final NamedParameterJdbcTemplate nameJdbc;

	@Override
	public List<ClienteOutputMinDto> listadoClientes() {
		String CLIENTES_QUERY = "SELECT c.id as id, c.nombre as nombre, c.apellido1 as apellido1, c.apellido2 as apellido2 "
						  	  + "FROM cliente c";
		return nameJdbc.query(CLIENTES_QUERY, new BeanPropertyRowMapper<>(ClienteOutputMinDto.class));
	}

	@Override
	public ClienteOutputDto infoClienteId(Integer codCliente) {
		String CLIENTE_QUERY = "SELECT c.id as id, c.nombre as nombre, c.apellido1 as apellido1, c.apellido2 as apellido2, c.telefono as telefono, c.dni as dni "
  						     + "FROM cliente c "
						     + "WHERE c.id = :codCliente";
		MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("codCliente", codCliente);
		try {
	        return nameJdbc.queryForObject(CLIENTE_QUERY, params, new BeanPropertyRowMapper<>(ClienteOutputDto.class));
	    } catch (EmptyResultDataAccessException e) {
	        return null;
	    }
	}

	@Override
	public int actualizarCliente(@ModelAttribute ClienteOutputDto clientes) {
        String CLIENTES_UPDATE = "UPDATE cliente SET nombre = :nombre, apellido1 = :apellido1, apellido2 = :apellido2, telefono = :telefono, dni = :dni "
        		   			   + "WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
        		.addValue("id", clientes.getId())
				.addValue("nombre", clientes.getNombre())
				.addValue("apellido1", clientes.getApellido1())
				.addValue("apellido2", clientes.getApellido2())
				.addValue("telefono", clientes.getTelefono())
				.addValue("dni", clientes.getDni());
        return nameJdbc.update(CLIENTES_UPDATE, params);
    }

	@Override
	public int insertarCliente(ClienteOutputDto cliente) {
		String CLIENTE_INSERT = "INSERT INTO cliente (nombre, apellido1, apellido2, telefono, dni) VALUES (:nombre, :apellido1, :apellido2, :telefono, :dni)";
		MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("nombre", cliente.getNombre())
                .addValue("apellido1", cliente.getApellido1())
                .addValue("apellido2", cliente.getApellido2())
                .addValue("telefono", cliente.getTelefono())
                .addValue("dni", cliente.getDni());
		return nameJdbc.update(CLIENTE_INSERT, params);
	}

}