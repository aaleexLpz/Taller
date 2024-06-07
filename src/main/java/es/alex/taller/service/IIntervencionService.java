package es.alex.taller.service;

import java.util.List;

import es.alex.taller.dto.intervencion.IntervencionOutputDto;

public interface IIntervencionService {

	List<IntervencionOutputDto> infoIntervencionId(Integer id);
	
}
