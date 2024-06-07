package es.alex.taller.dto.coche;

import lombok.Data;

@Data
public class CocheOutputMinDto {

	private Integer id;
	private String marca;
	private String modelo;
	private Integer codCliente;
}
