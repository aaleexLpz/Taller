package es.alex.taller.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import es.alex.taller.dto.intervencion.IntervencionOutputDto;
import es.alex.taller.dto.intervencion.IntervencionOutputMinDto;
import es.alex.taller.repository.IIntervencionRepo;
import es.alex.taller.service.IIntervencionService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IntervencionServiceImpl implements IIntervencionService{

	private final IIntervencionRepo intervencionRepo;
	
	@Override
	public IntervencionOutputDto infoIntervencionCodIntervencion(Integer codIntervencion) {
		return intervencionRepo.detallesIntervencion(codIntervencion);
	}

	@Override
	public List<IntervencionOutputMinDto> listadoIntervencionPorCoche(Integer codCoche) {
		return intervencionRepo.listaMinIntervencion(codCoche);
	}
	
	@Override
	public Integer insertarIntervenciones(IntervencionOutputDto intervenciones) {
		return intervencionRepo.insertarIntervencion(intervenciones);
	}

}
