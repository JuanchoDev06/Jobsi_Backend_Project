package com.escaes.jobsy.application.dto.institucion.ubicacion;

import java.util.List;

public record Departamento(String codigo, String nombre, List<Municipio> municipios) {
}
