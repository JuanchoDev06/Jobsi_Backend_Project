package com.escaes.jobsy.application.usecase.usuario;

import com.escaes.jobsy.domain.model.Usuario;
import com.escaes.jobsy.domain.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
@Service
public class GestionUsuariosUseCase {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    public GestionUsuariosUseCase(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    public void crearUsuario(Usuario usuario) {

        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo");
        }
        if(usuarioRepository.findByDocumento(usuario.documento()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un usuario con el documento proporcionado");
        }

        if (usuarioRepository.findByCorreo(usuario.correo()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un usuario con el correo proporcionado");
        }
        String encodedPassword = passwordEncoder.encode(usuario.clave());
        Usuario usuarioConClave = new Usuario(
                usuario.id(),
                usuario.nombre(),
                usuario.documento(),
                usuario.correo(),
                encodedPassword,
                usuario.bloqueado(),
                usuario.fechaNacimiento(),
                usuario.genero(),
                usuario.rol(),
                usuario.trabajos());
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
        Optional<Usuario> usuario = usuarioRepository.findByDocumento(documento);

        usuarioRepository.deleteByDocumento(documento);
    }
    public void eliminarUsuarioPorCorreo(String correo) {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
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
                usuario.clave(), true, usuario.fechaNacimiento(), usuario.genero(), usuario.rol(), usuario.trabajos());
        usuarioRepository.save(usuarioBloqueado);
    }
    public void desbloquearUsuarioPorCorreo(String correo) {
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con el correo proporcionado"));
        if (!usuario.bloqueado()) {
            throw new IllegalArgumentException("El usuario ya está desbloqueado");
        }
        Usuario usuarioDesbloqueado = new Usuario(usuario.id(), usuario.nombre(), usuario.documento(), usuario.correo(),
                usuario.clave(), false, usuario.fechaNacimiento(), usuario.genero(), usuario.rol(), usuario.trabajos());
        usuarioRepository.save(usuarioDesbloqueado);
    }

}
