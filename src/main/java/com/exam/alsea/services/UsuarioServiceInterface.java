package com.exam.alsea.services;

import com.exam.alsea.dtos.RequestUsuario;
import com.exam.alsea.dtos.RequestUpdateUsuario;
import com.exam.alsea.dtos.ResponseUsuario;
import com.exam.alsea.dtos.exceptionDtos.ResponseException;

import java.util.List;

public interface UsuarioServiceInterface {

    List<ResponseUsuario> getAllUsuarios() throws ResponseException;

    ResponseUsuario getUsuarioById(Long id) throws ResponseException;

    Boolean modificarUsuario(Long id, RequestUpdateUsuario requestUsuario) throws ResponseException;

    ResponseUsuario saveUsuario(RequestUsuario usuario) throws ResponseException;

    Boolean deleteUsuario(Long id) throws ResponseException;

}
