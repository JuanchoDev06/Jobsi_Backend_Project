package com.escaes.jobsy.application.usecase.trabajo;

import com.escaes.jobsy.domain.model.Trabajo;
import com.escaes.jobsy.domain.repository.TrabajoRepository;
import com.escaes.jobsy.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarTrabajosUseCase {

    private final TrabajoRepository trabajoRepository;

    private final UsuarioRepository usuarioRepository;

    public ListarTrabajosUseCase(
            TrabajoRepository trabajoRepository,
            UsuarioRepository usuarioRepository
    ){
        this.trabajoRepository = trabajoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Trabajo> listar(){
        return trabajoRepository.findAll();
    }


}
