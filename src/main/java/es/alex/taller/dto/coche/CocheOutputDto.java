package es.alex.taller.dto.coche;

import lombok.Data;

@Data
public class CocheOutputDto {

	private Integer id;
	private String marca;
	private String modelo;
	private String color;
	private String matricula;
	private Integer codCliente;
	
}
