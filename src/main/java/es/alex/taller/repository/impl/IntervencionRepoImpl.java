package es.alex.taller.repository.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
		String INTERVENCION_QUERY = "SELECT i.id as id, i.resumen as resumen, i.texto as texto, i.kilometros as kilometros, i.precio as precio "
							      + "FROM intervencion i "
							      + "WHERE i.codCoche  = :codCoche";
		return nameJdbc.query(INTERVENCION_QUERY,  new BeanPropertyRowMapper<>(IntervencionOutputMinDto.class));
	}

	@Override
	public IntervencionOutputDto detalleIntervencion(Integer codCoche) {
		// TODO Auto-generated method stub
		return null;
	}

}
