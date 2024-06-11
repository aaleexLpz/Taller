package es.alex.taller.service;

import java.util.List;

import es.alex.taller.dto.coche.CocheOutputDto;
import es.alex.taller.dto.coche.CocheOutputMinDto;
import es.alex.taller.dto.intervencion.IntervencionOutputMinDto;

public interface ICocheService {

	CocheOutputDto infoCocheId(Integer codCoche);
	
	List<CocheOutputMinDto> listadoCochesPorCliente(Integer codCliente);
	
	void actualizarCoche(CocheOutputDto coches);
	
	Integer insertarCoche(CocheOutputDto coches);
	
	Integer eliminarCoche(Integer codCoche);
	
	Integer obtenerCodClientePorCoche(Integer codCoche);
}