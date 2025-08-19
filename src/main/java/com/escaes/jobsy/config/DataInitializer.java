package com.escaes.jobsy.config;

import com.escaes.jobsy.application.usecase.genero.GestionGenerosUseCase;
import com.escaes.jobsy.application.usecase.genero.ListarGenerosUseCase;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DataInitializer implements CommandLineRunner {

    private final GestionGenerosUseCase gestionGenerosUseCase;

    private final ListarGenerosUseCase listarGenerosUseCase;

    public DataInitializer(GestionGenerosUseCase gestionGenerosUseCase,
                           ListarGenerosUseCase listarGenerosUseCase) {
        this.gestionGenerosUseCase = gestionGenerosUseCase;
        this.listarGenerosUseCase = listarGenerosUseCase;
    }

    @Override
    public void run(String... args) throws Exception {
        initializeGeneros();
    }

    private void initializeGeneros() {
        if(listarGenerosUseCase.contarGeneros()==0){
            gestionGenerosUseCase.crearGenero("Masculino");
            gestionGenerosUseCase.crearGenero("Femenino");
            gestionGenerosUseCase.crearGenero("Alien");
            gestionGenerosUseCase.crearGenero("Otro");
        }

    }
}
