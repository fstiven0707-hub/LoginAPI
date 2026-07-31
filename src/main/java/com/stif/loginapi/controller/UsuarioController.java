package com.stif.loginapi.controller;

import com.stif.loginapi.dto.ApiResponse;
import com.stif.loginapi.dto.UsuarioResponse;
import com.stif.loginapi.entity.Usuario;
import com.stif.loginapi.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

}