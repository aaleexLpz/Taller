package es.alex.taller.repository.impl;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;

import es.alex.taller.dto.coche.CocheOutputDto;
import es.alex.taller.dto.coche.CocheOutputMinDto;
import es.alex.taller.dto.intervencion.IntervencionOutputMinDto;
import es.alex.taller.repository.ICocheRepo;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CocheRepoImpl implements ICocheRepo{

	private final NamedParameterJdbcTemplate nameJdbc;
	
	@Override
    public List<CocheOutputMinDto> listadoCochesPorCliente(Integer codCliente) {
        final String COCHES_POR_CLIENTE_QUERY = "SELECT c.id as id, c.marca as marca, c.modelo as modelo "
                                              + "FROM coche c "
                                              + "WHERE c.codCliente = :codCliente";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("codCliente", codCliente);
        return nameJdbc.query(COCHES_POR_CLIENTE_QUERY, params, new BeanPropertyRowMapper<>(CocheOutputMinDto.class));
    }

	@Override
	public CocheOutputDto detallesCoche(Integer codCoche) {
	    final String DETALLES_COCHES_QUERY = "SELECT c.id as id, c.marca as marca, c.modelo as modelo, c.color as color, c.matricula as matricula "
	                                       + "FROM coche c "
	                                       + "WHERE c.id = :codCoche";
	    MapSqlParameterSource params = new MapSqlParameterSource()
	            .addValue("codCoche", codCoche);
	    return nameJdbc.queryForObject(DETALLES_COCHES_QUERY, params, new BeanPropertyRowMapper<>(CocheOutputDto.class));
	}

	
	@Override
	public int actualizarCoche(@ModelAttribute CocheOutputDto coches) {
        String COCHES_UPDATE = "UPDATE coche SET marca = :marca, modelo = :modelo, color = :color, matricula = :matricula "
        		   			 + "WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
        		.addValue("id", coches.getId())
				.addValue("marca", coches.getMarca())
				.addValue("modelo", coches.getModelo())
				.addValue("color", coches.getColor())
				.addValue("matricula", coches.getMatricula());
        return nameJdbc.update(COCHES_UPDATE, params);
    }

	@Override
	public int insertarCoche(CocheOutputDto coche) {
		String COCHE_INSERT = "INSERT INTO coche (marca, modelo, color, matricula, codCliente) VALUES (:marca, :modelo, :color, :matricula, :codCliente)";
		MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("marca", coche.getMarca())
                .addValue("modelo", coche.getModelo())
                .addValue("color", coche.getColor())
                .addValue("matricula", coche.getMatricula())
                .addValue("codCliente", coche.getCodCliente());
		return nameJdbc.update(COCHE_INSERT, params);
	}
	
	@Override
	public List<IntervencionOutputMinDto> listadoIntervencionPorCoche(Integer codCoche) {
		String INTERVENCION_QUERY = "SELECT i.id as id, i.resumen as resumen, i.texto as texto, i.kilometros as kilometros, i.precio as precio "
								  + "FROM intervencion i "
								  + "WHERE i.codCoche  = :codCoche";
		MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("codCoche", codCoche);
		return nameJdbc.query(INTERVENCION_QUERY, params, new BeanPropertyRowMapper<>(IntervencionOutputMinDto.class));
	}

}