package es.alex.taller.dto.cliente;

import java.util.List;

import es.alex.taller.dto.coche.CocheOutputMinDto;
import lombok.Data;

@Data
public class ClienteOutputDto {

	private Integer id;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String telefono;
	private String dni;
	private List<CocheOutputMinDto> coches;
}
