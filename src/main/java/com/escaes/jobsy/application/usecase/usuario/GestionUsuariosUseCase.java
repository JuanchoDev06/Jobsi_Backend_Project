package com.escaes.jobsy.application.usecase.usuario;

import com.escaes.jobsy.application.dto.usuario.UsuarioRequest;
import com.escaes.jobsy.domain.model.Sexo;
import com.escaes.jobsy.domain.model.Rol;
import com.escaes.jobsy.domain.model.Usuario;
import com.escaes.jobsy.domain.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class GestionUsuariosUseCase {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;


    public void crearUsuario(UsuarioRequest request, Sexo genero, Rol rol) {

        if (request == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo");
        }
        if(usuarioRepository.findByDocumento(request.documento()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un usuario con el documento proporcionado");
        }

        if (usuarioRepository.findByCorreo(request.email()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un usuario con el correo proporcionado");
        }
        String encodedPassword = passwordEncoder.encode(request.password());
        Usuario usuarioConClave = new Usuario(
                UUID.randomUUID(),
                request.nombre(),
                request.documento(),
                request.email(),
                encodedPassword,
                false,
                request.fechaNacimiento(),
                genero,
                rol,
                List.of(),
                List.of());
        usuarioRepository.save(usuarioConClave);
    }

    public Usuario obtenerUsuarioPorId(UUID id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con el ID proporcionado"));
    }

    public Usuario obtenerUsuarioPorDocumento(Integer documento) {
        return usuarioRepository.findByDocumento(documento)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con el documento proporcionado"));
    }

    public Usuario obtenerUsuarioPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con el correo proporcionado"));
    }

    public void actualizarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo");
        }
        if (usuarioRepository.findById(usuario.id()).isEmpty()) {
            throw new IllegalArgumentException("Usuario no encontrado con el ID proporcionado");
        }
        usuarioRepository.save(usuario);
    }
    public void eliminarUsuarioPorId(UUID id) {
        if (usuarioRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Usuario no encontrado con el ID proporcionado");
        }
        usuarioRepository.deleteById(id);
    }
    public void eliminarUsuarioPorDocumento(Integer documento) {
        usuarioRepository.findByDocumento(documento);

        usuarioRepository.deleteByDocumento(documento);
    }
    public void eliminarUsuarioPorCorreo(String correo) {
        usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con el correo proporcionado"));
        usuarioRepository.deleteByCorreo(correo);
    }

    public void eliminarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo");
        }
        if (usuarioRepository.findById(usuario.id()).isEmpty()) {
            throw new IllegalArgumentException("Usuario no encontrado con el ID proporcionado");
        }
        usuarioRepository.delete(usuario);
    }
    public void bloquearUsuarioPorCorreo(String correo) {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con el correo proporcionado"));
        if (usuario.bloqueado()) {
            throw new IllegalArgumentException("El usuario ya está bloqueado");
        }
        Usuario usuarioBloqueado = new Usuario(usuario.id(), usuario.nombre(), usuario.documento(), usuario.correo(),
                usuario.clave(), true, usuario.fechaNacimiento(), usuario.genero(), usuario.rol(), usuario.trabajos(),usuario.trabajosRealizados());
        usuarioRepository.save(usuarioBloqueado);
    }
    public void desbloquearUsuarioPorCorreo(String correo) {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con el correo proporcionado"));
        if (!usuario.bloqueado()) {
            throw new IllegalArgumentException("El usuario ya está desbloqueado");
        }
        Usuario usuarioDesbloqueado = new Usuario(usuario.id(), usuario.nombre(), usuario.documento(), usuario.correo(),
                usuario.clave(), false, usuario.fechaNacimiento(), usuario.genero(), usuario.rol(), usuario.trabajos(),usuario.trabajosRealizados());
        usuarioRepository.save(usuarioDesbloqueado);
    }

}
