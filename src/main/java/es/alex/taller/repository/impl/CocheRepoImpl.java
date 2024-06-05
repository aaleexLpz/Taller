package es.alex.taller.repository.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import es.alex.taller.dto.coche.CocheOutputDto;
import es.alex.taller.dto.coche.CocheOutputMinDto;
import es.alex.taller.dto.intervencion.IntervencionOutputMinDto;
import es.alex.taller.repository.ICocheRepo;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CocheRepoImpl implements ICocheRepo{

	private final NamedParameterJdbcTemplate namedJdbcTemplate;
	
	@Override
    public List<CocheOutputMinDto> listadoCochesPorCliente(Integer codCliente) {
        final String COCHES_POR_CLIENTE_QUERY = "SELECT c.id as id, c.marca as marca, c.modelo as modelo "
                                              + "FROM coche c"
                                              + "WHERE codCliente = :codCliente";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("codCliente", codCliente);
        return namedJdbcTemplate.query(COCHES_POR_CLIENTE_QUERY, params, new BeanPropertyRowMapper<>(CocheOutputMinDto.class));
    }

	@Override
	public CocheOutputDto detallesCoche(Integer codCliente) {
		final String DETALLES_COCHES_QUERY = "SELECT c.id as id, c.marca as marca, c.modelo as modelo, c.color as color, c.matricula as matricula "
										   + "FROM coche c "
										   + "WHERE c.codCliente = :codCliente";
		MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("codCliente", codCliente);
		return namedJdbcTemplate.queryForObject(DETALLES_COCHES_QUERY, params, new BeanPropertyRowMapper<>(CocheOutputDto.class));
	}

}
