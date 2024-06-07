package es.alex.taller.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import es.alex.taller.dto.intervencion.IntervencionOutputDto;
import es.alex.taller.repository.IIntervencionRepo;
import es.alex.taller.service.IIntervencionService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IntervencionServiceImpl implements IIntervencionService{

	private final IIntervencionRepo intervencionRepo;
	
	@Override
	public List<IntervencionOutputDto> infoIntervencionId(Integer id) {
		return intervencionRepo.listaIntervenciones(id);
	}

}
