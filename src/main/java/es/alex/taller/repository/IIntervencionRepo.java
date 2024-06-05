package es.alex.taller.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.alex.taller.dto.intervencion.IntervencionOutputDto;
import es.alex.taller.dto.intervencion.IntervencionOutputMinDto;

@Repository
public interface IIntervencionRepo {

	List<IntervencionOutputMinDto> listaMinIntervencion(Integer codCoche);
	
	IntervencionOutputDto detalleIntervencion(Integer codCoche);
	
}
