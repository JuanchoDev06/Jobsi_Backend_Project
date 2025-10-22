package com.escaes.jobsy.application.usecase.trabajo;

import com.escaes.jobsy.domain.model.Trabajo;
import com.escaes.jobsy.domain.repository.TrabajoRepository;
import com.escaes.jobsy.domain.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarTrabajosUseCase {

    private final TrabajoRepository trabajoRepository;

    private final UsuarioRepository usuarioRepository;

    public List<Trabajo> listar(){
        return trabajoRepository.findAll();
    }


}
