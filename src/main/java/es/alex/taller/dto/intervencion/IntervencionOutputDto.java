package es.alex.taller.dto.intervencion;

import lombok.Data;

@Data
public class IntervencionOutputDto {

	private Integer id;
	private String resumen;
	private String texto;
	private Integer kilometros;
	private Integer codCoche;
	private Integer precio;
}
