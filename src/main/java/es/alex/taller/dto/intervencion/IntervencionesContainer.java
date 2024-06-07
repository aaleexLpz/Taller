package es.alex.taller.dto.intervencion;

import java.util.List;

import lombok.Data;

@Data
public class IntervencionesContainer {
    private List<IntervencionOutputDto> intervenciones;

    public void setIntervenciones(List<IntervencionOutputDto> intervenciones) {
        this.intervenciones = intervenciones;
    }
}
