package es.alex.taller.service;

import java.util.List;

import es.alex.taller.dto.intervencion.IntervencionOutputDto;
import es.alex.taller.dto.intervencion.IntervencionOutputMinDto;

public interface IIntervencionService {

	IntervencionOutputDto infoIntervencionCodIntervencion(Integer codIntervencion);
	
	List<IntervencionOutputMinDto> listadoIntervencionPorCoche(Integer codCoche);
	
	Integer insertarIntervencion(IntervencionOutputDto intervenciones);
	
	IntervencionOutputDto codCocheCodIntervencion(Integer codIntervencion);
	
	void eliminarIntervencion(Integer codIntervencion);
}
