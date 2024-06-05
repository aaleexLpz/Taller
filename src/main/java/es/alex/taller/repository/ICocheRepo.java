package es.alex.taller.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.alex.taller.dto.coche.CocheOutputDto;
import es.alex.taller.dto.coche.CocheOutputMinDto;

@Repository
public interface ICocheRepo {

	List<CocheOutputMinDto> listadoCochesPorCliente(Integer codCliente);
	
	CocheOutputDto detallesCoche(Integer codCliente);
	
}
