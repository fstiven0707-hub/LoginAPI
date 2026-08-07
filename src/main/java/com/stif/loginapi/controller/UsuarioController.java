package com.stif.loginapi.controller;

import com.stif.loginapi.dto.ApiResponse;
import com.stif.loginapi.dto.UsuarioResponse;
import com.stif.loginapi.entity.Usuario;
import com.stif.loginapi.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Registrar un nuevo usuario
     */
    @PostMapping("/registro")
    public ResponseEntity<ApiResponse> registrar(@RequestBody Usuario usuario) {

        Usuario nuevoUsuario = usuarioService.registrar(usuario);

        UsuarioResponse datosUsuario = new UsuarioResponse(
                nuevoUsuario.getId(),
                nuevoUsuario.getNombre(),
                nuevoUsuario.getCorreo()
        );

        ApiResponse respuesta = new ApiResponse(
                "Usuario registrado correctamente",
                datosUsuario
        );

        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }

    /**
     * Iniciar sesión
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody Usuario usuario) {

        Usuario usuarioLogin = usuarioService.login(
                usuario.getCorreo(),
                usuario.getPassword()
        );

        UsuarioResponse datosUsuario = new UsuarioResponse(
                usuarioLogin.getId(),
                usuarioLogin.getNombre(),
                usuarioLogin.getCorreo()
        );

        ApiResponse respuesta = new ApiResponse(
                "Autenticación satisfactoria",
                datosUsuario
        );

        return ResponseEntity.ok(respuesta);
    }

    /**
     * Listar todos los usuarios
     */
    @GetMapping("/usuarios")
    public ResponseEntity<ApiResponse> listarUsuarios() {

        List<UsuarioResponse> usuarios = usuarioService.listarUsuarios()
                .stream()
                .map(usuario -> new UsuarioResponse(
                        usuario.getId(),
                        usuario.getNombre(),
                        usuario.getCorreo()
                ))
                .collect(Collectors.toList());

        ApiResponse respuesta = new ApiResponse(
                "Lista de usuarios obtenida correctamente",
                usuarios
        );

        return ResponseEntity.ok(respuesta);
    }

    /**
     * Buscar usuario por ID
     */
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<ApiResponse> buscarPorId(@PathVariable Long id) {

        Usuario usuario = usuarioService.buscarPorId(id);

        UsuarioResponse datosUsuario = new UsuarioResponse(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreo()
        );

        ApiResponse respuesta = new ApiResponse(
                "Usuario encontrado",
                datosUsuario
        );

        return ResponseEntity.ok(respuesta);
    }

    /**
     * Actualizar usuario
     */
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<ApiResponse> actualizar(
            @PathVariable Long id,
            @RequestBody Usuario usuario) {

        Usuario usuarioActualizado = usuarioService.actualizar(id, usuario);

        UsuarioResponse datosUsuario = new UsuarioResponse(
                usuarioActualizado.getId(),
                usuarioActualizado.getNombre(),
                usuarioActualizado.getCorreo()
        );

        ApiResponse respuesta = new ApiResponse(
                "Usuario actualizado correctamente",
                datosUsuario
        );

        return ResponseEntity.ok(respuesta);
    }

    /**
     * Eliminar usuario
     */
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<ApiResponse> eliminar(@PathVariable Long id) {

        usuarioService.eliminar(id);

        ApiResponse respuesta = new ApiResponse(
                "Usuario eliminado correctamente",
                null
        );

        return ResponseEntity.ok(respuesta);
    }
}