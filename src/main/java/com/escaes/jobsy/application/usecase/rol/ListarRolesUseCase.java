package com.escaes.jobsy.application.usecase.rol;

import com.escaes.jobsy.domain.model.Rol;
import com.escaes.jobsy.domain.repository.RolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarRolesUseCase {

    private final RolRepository rolRepository;

    public ListarRolesUseCase(RolRepository rolRepository){
        this.rolRepository=rolRepository;
    }

    public int contarRoles(){
        return rolRepository.findAll().size();
    }

    public List<Rol> listarRoles(){
        return rolRepository.findAll();
    }
}
