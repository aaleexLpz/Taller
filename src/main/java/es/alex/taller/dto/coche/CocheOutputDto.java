package es.alex.taller.dto.coche;

import java.util.List;

import es.alex.taller.dto.intervencion.IntervencionOutputDto;
import es.alex.taller.dto.intervencion.IntervencionOutputMinDto;
import lombok.Data;

@Data
public class CocheOutputDto {

	private Integer id;
	private String marca;
	private String modelo;
	private String color;
	private String matricula;
	private Integer codCliente;
	private List<IntervencionOutputMinDto> intervenciones;
	
}
