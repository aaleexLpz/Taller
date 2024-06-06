package es.alex.taller.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import es.alex.taller.dto.coche.CocheOutputDto;
import es.alex.taller.dto.intervencion.IntervencionOutputMinDto;
import es.alex.taller.repository.ICocheRepo;
import es.alex.taller.repository.IIntervencionRepo;
import es.alex.taller.service.ICocheService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CocheServiceImpl implements ICocheService {
	
	private final ICocheRepo cocheRepo;
	private final IIntervencionRepo intervencionRepo;
	
	@Override
	public CocheOutputDto detallesCoche(Integer codCoche) {
		CocheOutputDto cocheOutput = cocheRepo.detallesCoche(codCoche);
		List<IntervencionOutputMinDto> intervenciones = intervencionRepo.listaMinIntervencion(codCoche);
		cocheOutput.setIntervenciones(intervenciones);
		return cocheOutput;
	}

	@Override
	public void actualizarCoches(CocheOutputDto coches) {
		cocheRepo.actualizarCoche(coches);
	}

}