package com.stif.loginapi.service;

import com.stif.loginapi.entity.Usuario;
import com.stif.loginapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Registrar un nuevo usuario
     */
    public Usuario registrar(Usuario usuario) {

        if (usuario.getNombre() == null || usuario.getNombre().isBlank()) {
            throw new RuntimeException("El nombre es obligatorio");
        }

        if (usuario.getCorreo() == null || usuario.getCorreo().isBlank()) {
            throw new RuntimeException("El correo es obligatorio");
        }

        if (usuario.getPassword() == null || usuario.getPassword().isBlank()) {
            throw new RuntimeException("La contraseña es obligatoria");
        }

        // Normalizar datos
        usuario.setNombre(usuario.getNombre().trim());
        usuario.setCorreo(usuario.getCorreo().trim().toLowerCase());
        usuario.setPassword(usuario.getPassword().trim());

        // Validar correo repetido
        if (usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent()) {
            throw new RuntimeException("El correo ya está registrado");
        }

        return usuarioRepository.save(usuario);
    }

    /**
     * Inicio de sesión
     */
    public Usuario login(String correo, String password) {

        if (correo == null || correo.isBlank()) {
            throw new RuntimeException("El correo es obligatorio");
        }

        if (password == null || password.isBlank()) {
            throw new RuntimeException("La contraseña es obligatoria");
        }

        correo = correo.trim().toLowerCase();
        password = password.trim();

        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!usuario.getPassword().trim().equals(password)) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return usuario;
    }

    /**
     * Listar todos los usuarios
     */
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    /**
     * Buscar usuario por ID
     */
    public Usuario buscarPorId(Long id) {

        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    }

    /**
     * Actualizar usuario
     */
    public Usuario actualizar(Long id, Usuario usuarioActualizado) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (usuarioActualizado.getNombre() == null || usuarioActualizado.getNombre().isBlank()) {
            throw new RuntimeException("El nombre es obligatorio");
        }

        if (usuarioActualizado.getCorreo() == null || usuarioActualizado.getCorreo().isBlank()) {
            throw new RuntimeException("El correo es obligatorio");
        }

        if (usuarioActualizado.getPassword() == null || usuarioActualizado.getPassword().isBlank()) {
            throw new RuntimeException("La contraseña es obligatoria");
        }

        String correo = usuarioActualizado.getCorreo().trim().toLowerCase();

        usuarioRepository.findByCorreo(correo).ifPresent(u -> {
            if (!u.getId().equals(id)) {
                throw new RuntimeException("El correo ya está registrado");
            }
        });

        usuario.setNombre(usuarioActualizado.getNombre().trim());
        usuario.setCorreo(correo);
        usuario.setPassword(usuarioActualizado.getPassword().trim());

        return usuarioRepository.save(usuario);
    }

    /**
     * Eliminar usuario
     */
    public void eliminar(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuarioRepository.delete(usuario);
    }
}