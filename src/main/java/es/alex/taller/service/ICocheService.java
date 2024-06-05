package es.alex.taller.service;

import es.alex.taller.dto.coche.CocheOutputDto;

public interface ICocheService {

	CocheOutputDto detallesCoche(Integer codCoche);
	
}
