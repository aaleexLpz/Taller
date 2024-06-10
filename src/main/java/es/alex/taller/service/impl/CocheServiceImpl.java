package es.alex.taller.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import es.alex.taller.dto.coche.CocheOutputDto;
import es.alex.taller.dto.coche.CocheOutputMinDto;
import es.alex.taller.dto.intervencion.IntervencionOutputMinDto;
import es.alex.taller.repository.ICocheRepo;
import es.alex.taller.repository.IIntervencionRepo;
import es.alex.taller.service.ICocheService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CocheServiceImpl implements ICocheService {
	
	private final ICocheRepo cocheRepo;
	
	@Override
	public CocheOutputDto infoCocheId(Integer codCliente) {
		CocheOutputDto cocheOutput = cocheRepo.detallesCoche(codCliente);
		List<IntervencionOutputMinDto> intervenciones = cocheRepo.listadoIntervencionPorCoche(codCliente);
		cocheOutput.setIntervenciones(intervenciones);
		return cocheOutput;
	}

	@Override
	public void actualizarCoches(CocheOutputDto coches) {
		cocheRepo.actualizarCoche(coches);
	}

	@Override
	public List<CocheOutputMinDto> listadoCochesPorCliente(Integer codCliente) {
		return cocheRepo.listadoCochesPorCliente(codCliente);
	}

	@Override
	public void insertarCoches(CocheOutputDto coches) {
		cocheRepo.insertarCoche(coches);
	}
	
}