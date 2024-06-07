package es.alex.taller.service;

import java.util.List;

import es.alex.taller.dto.coche.CocheOutputDto;
import es.alex.taller.dto.coche.CocheOutputMinDto;
import es.alex.taller.dto.intervencion.IntervencionOutputMinDto;

public interface ICocheService {

	CocheOutputDto infoCocheId(Integer codCoche);
	
	List<CocheOutputMinDto> listadoCochesPorCliente(Integer codCliente);
	
	public void actualizarCoches(CocheOutputDto coches);
	
	List<IntervencionOutputMinDto> listadoIntervencionPorCoche(Integer codCoche);
}