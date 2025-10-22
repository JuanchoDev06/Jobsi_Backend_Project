package com.escaes.jobsy.application.usecase.rol;

import com.escaes.jobsy.domain.model.Rol;
import com.escaes.jobsy.domain.repository.RolRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GestionRolesUseCase {

    private final RolRepository rolRepository;


    public void crearRol(Rol rol) {
        if (rol.nombre() == null || rol.nombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del rol no puede ser nulo o vacío");
        }
        if(rolRepository.findByNombre(rol.nombre()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un rol con el nombre proporcionado");
        }
        Rol nuevoRol = new Rol(UUID.randomUUID(), rol.nombre(),rol.descripcion());
        rolRepository.save(nuevoRol);
    }
    public void updateRol(Rol rol) {
        if (rol.nombre() == null || rol.nombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del rol no puede ser nulo o vacío");
        }
        if (rolRepository.findById(rol.id()).isEmpty()) {
            throw new IllegalArgumentException("Rol no encontrado con el ID proporcionado");
        }
        rolRepository.save(rol);
    }

    public Rol obtenerRolPorId(UUID id) {
        return rolRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado con el ID proporcionado"));
    }

    public Rol obtenerRolPorNombre(String nombre) {
        return rolRepository.findByNombre(nombre)
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado con el nombre proporcionado"));
    }

    public void eliminarRolPorId(UUID id) {
        if (rolRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Rol no encontrado con el ID proporcionado");
        }
        rolRepository.deleteById(id);
    }
    public void eliminarRolPorNombre(String nombre) {
        if (rolRepository.findByNombre(nombre).isEmpty()) {
            throw new IllegalArgumentException("Rol no encontrado con el nombre proporcionado");
        }
        rolRepository.deleteByNombre(nombre);
    }
}
