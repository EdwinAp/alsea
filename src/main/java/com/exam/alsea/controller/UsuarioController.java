package com.exam.alsea.controller;

import com.exam.alsea.dtos.RequestUsuario;
import com.exam.alsea.dtos.RequestUpdateUsuario;
import com.exam.alsea.dtos.ResponseUsuario;
import com.exam.alsea.dtos.exceptionDtos.ResponseException;
import com.exam.alsea.entity.Usuario;
import com.exam.alsea.services.UsuarioServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServiceInterface usuarioServiceInterface;

    @GetMapping
    public ResponseEntity<?> getAllUsuarios() {
        try {
            return ResponseEntity.ok(usuarioServiceInterface.getAllUsuarios());
        } catch (ResponseException responseException) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseException.getResponse());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(usuarioServiceInterface.getUsuarioById(id));
        } catch (ResponseException responseException) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseException.getResponse());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> modificarUsuario(
            @PathVariable Long id,
            @RequestBody RequestUpdateUsuario requestUsuario
    ) {
        try {
            return ResponseEntity.ok(usuarioServiceInterface
                    .modificarUsuario(id, requestUsuario) ? "Usuario modificado correctamente"
                    : "No se ha actualizado");
        } catch (ResponseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getResponse());
        }
    }

    @PostMapping
    public ResponseEntity<?> createUsuario(@RequestBody RequestUsuario usuario) {
        try {
             ResponseUsuario responseUsuario = usuarioServiceInterface.saveUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseUsuario);
        } catch (ResponseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getResponse());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(usuarioServiceInterface.deleteUsuario(id) ? "Eliminado correctamente"
                    : "No se ha eliminado el registro");
        } catch (ResponseException responseException) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseException.getResponse());
        }
    }
}

