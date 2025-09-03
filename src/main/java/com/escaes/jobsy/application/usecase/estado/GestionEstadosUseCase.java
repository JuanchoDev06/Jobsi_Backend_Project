package com.escaes.jobsy.application.usecase.estado;

import com.escaes.jobsy.domain.model.Estado;
import com.escaes.jobsy.domain.repository.EstadoRepository;
import org.springframework.stereotype.Service;

@Service
public class GestionEstadosUseCase {

    private final EstadoRepository estadoRepository;

    public GestionEstadosUseCase(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public void crearEstado(String nombreEstado){
        if (nombreEstado == null){
            throw new IllegalArgumentException("El nombre de estado es null");
        }
        if(estadoRepository.findByNombre(nombreEstado).isPresent()){
            throw new IllegalArgumentException("El nombre de estado ya existe");
        }
        Estado estado = new Estado(null,nombreEstado);

        estadoRepository.save(estado);
    }
    public Estado obtenerEstadoPorNombre(String nombreEstado){
        if (nombreEstado == null){
            throw new IllegalArgumentException("El nombre de estado es null");
        }
        return estadoRepository.findByNombre(nombreEstado)
                .orElseThrow(() -> new IllegalArgumentException("El nombre de estado no existe"));
    }

    public Estado obtenerEstadoPorId(Long idEstado){
        if (idEstado == null){
            throw new IllegalArgumentException("El identificador de estado es null");
        }
        return estadoRepository
                .findById(idEstado).orElseThrow(()-> new IllegalArgumentException("El identificador de estado no existe"));
    }

    public void actualizarEstado(String nombreEstado, String nuevoEstado){
        if (nombreEstado == null){
            throw new IllegalArgumentException("El estado no puede ser nulo");
        }
        Estado estado= estadoRepository.findByNombre(nombreEstado)
                .orElseThrow(()-> new IllegalArgumentException("El estado no existe"));

        Estado actualizado= new Estado(
          estado.id(),nuevoEstado
        );
        estadoRepository.save(actualizado);
    }
    public void eliminarEstadoPorId(Long idEstado){
        if (idEstado == null){
            throw new IllegalArgumentException("El identificador de estado es null");
        }
        estadoRepository.deleteById(idEstado);
    }
    public void eliminarEstadoPorNombre(String nombreEstado){
        if (nombreEstado == null){
            throw new IllegalArgumentException("El nombre de estado es null");
        }
        estadoRepository.deleteByNombre(nombreEstado);
    }
}
