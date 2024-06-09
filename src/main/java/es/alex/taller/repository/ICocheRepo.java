package es.alex.taller.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;

import es.alex.taller.dto.coche.CocheOutputDto;
import es.alex.taller.dto.coche.CocheOutputMinDto;
import es.alex.taller.dto.intervencion.IntervencionOutputMinDto;

@Repository
public interface ICocheRepo {

	List<CocheOutputMinDto> listadoCochesPorCliente(Integer codCliente);
	
	CocheOutputDto detallesCoche(Integer codCliente);
	
	int actualizarCoche(@ModelAttribute CocheOutputDto coches);
	
	List<IntervencionOutputMinDto> listadoIntervencionPorCoche(Integer codCoche);
	
	int insertarCoche(CocheOutputDto coches);
}