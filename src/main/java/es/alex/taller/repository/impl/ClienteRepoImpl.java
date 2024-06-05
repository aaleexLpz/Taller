package es.alex.taller.repository.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import es.alex.taller.dto.cliente.ClienteOutputDto;
import es.alex.taller.dto.cliente.ClienteOutputMinDto;
import es.alex.taller.repository.IClienteRepo;
import lombok.RequiredArgsConstructor;


@Repository
@RequiredArgsConstructor
public class ClienteRepoImpl implements IClienteRepo { 

	private final NamedParameterJdbcTemplate nameJdbc;
	

	@Override
	public List<ClienteOutputDto> listadoClientes() {
		final String CLIENTES_QUERY = "SELECT c.id as id, c.nombre as nombre, c.apellido1 as apellido1, c.apellido2 as apellido2, c.telefono as telefono, c.dni as dni "
							  		+ "FROM cliente c";
		return nameJdbc.query(CLIENTES_QUERY, new BeanPropertyRowMapper<>(ClienteOutputDto.class));
	}

	@Override
	public ClienteOutputDto infoClienteId(Integer codCliente) {
		final String CLIENTE_QUERY = "SELECT c.nombre as nombre, c.apellido1 as apellido1, c.apellido2 as apellido2, c.telefono as telefono, c.dni as dni "
		  						   + "FROM cliente c "
  								   + "WHERE c.id = :codCliente";
		MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("codCliente", codCliente);
		return nameJdbc.queryForObject(CLIENTE_QUERY, params, new BeanPropertyRowMapper<>(ClienteOutputDto.class));
	}

}