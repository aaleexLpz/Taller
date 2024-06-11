package es.alex.taller.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import es.alex.taller.dto.intervencion.IntervencionOutputDto;
import es.alex.taller.dto.intervencion.IntervencionOutputMinDto;
import es.alex.taller.repository.IIntervencionRepo;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class IntervencionRepoImpl implements IIntervencionRepo{

	private final NamedParameterJdbcTemplate nameJdbc;
	
	@Override
	public List<IntervencionOutputMinDto> listaMinIntervencion(Integer codCoche) {
		String INTERVENCION_QUERY = "SELECT i.id as id, i.resumen as resumen, i.precio as precio "
							      + "FROM intervencion i "
							      + "WHERE i.codCoche  = :codCoche";
		MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("codCoche", codCoche);
		return nameJdbc.query(INTERVENCION_QUERY, params, new BeanPropertyRowMapper<>(IntervencionOutputMinDto.class));
	}

	@Override
	public IntervencionOutputDto detallesIntervencion(Integer codIntervencion) {
	    String INTERVENCIONES_QUERY = "SELECT i.id as id, i.resumen as resumen, i.texto as texto, i.kilometros as kilometros, i.precio as precio, i.codCoche as codCoche "
	                                + "FROM intervencion i "
	                                + "WHERE i.id  = :codIntervencion";
	    MapSqlParameterSource params = new MapSqlParameterSource()
	            .addValue("codIntervencion", codIntervencion);
	    return nameJdbc.queryForObject(INTERVENCIONES_QUERY, params, new BeanPropertyRowMapper<>(IntervencionOutputDto.class));
	}

	@Override
	public Integer insertarIntervencion(IntervencionOutputDto intervencion) {
		String INTERVENCION_INSERT = "INSERT INTO intervencion (resumen, texto, kilometros, precio, codCoche) VALUES (:resumen, :texto, :kilometros, :precio, :codCoche)";
		MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("resumen", intervencion.getResumen())
                .addValue("texto", intervencion.getTexto())
                .addValue("kilometros", intervencion.getKilometros())
                .addValue("precio", intervencion.getPrecio())
                .addValue("codCoche", intervencion.getCodCoche());
		KeyHolder kh = new GeneratedKeyHolder();
	    nameJdbc.update(INTERVENCION_INSERT, params, kh);
	    return kh.getKey().intValue();
	}

	@Override
	public void eliminarIntervencion(Integer codIntervencion) {
		String INTERVENCION_DELETE = "DELETE FROM intervencion WHERE id = :codIntervencion";
		MapSqlParameterSource params = new MapSqlParameterSource()
	            .addValue("codIntervencion", codIntervencion);
		nameJdbc.update(INTERVENCION_DELETE, params);
	}
	
	@Override
	public IntervencionOutputDto codCocheIntervencion(Integer codIntervencion) {
		String COD_COCHE_INTERVENCION_QUERY = "SELECT i.codCoche as CodCoche "
											+ "FROM intervencion i "
											+ "WHERE i.id = :codIntervencion";
		MapSqlParameterSource params = new MapSqlParameterSource()
	            .addValue("codIntervencion", codIntervencion);
		try {
	        return nameJdbc.queryForObject(COD_COCHE_INTERVENCION_QUERY, params, new BeanPropertyRowMapper<>(IntervencionOutputDto.class));
	    } catch (EmptyResultDataAccessException e) {
	        return null;
	    }
	}


}