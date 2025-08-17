package com.escaes.jobsy.domain.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record Usuario(UUID id,String nombre ,Integer documento, String correo,
                      String clave, Boolean bloqueado,
                      Date fechaNacimiento,
                      Genero genero,Rol rol, List<Trabajo>trabajos) {
}