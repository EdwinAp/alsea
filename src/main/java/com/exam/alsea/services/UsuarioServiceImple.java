package com.exam.alsea.services;

import com.exam.alsea.dtos.RequestUsuario;
import com.exam.alsea.dtos.RequestUpdateUsuario;
import com.exam.alsea.dtos.ResponseUsuario;
import com.exam.alsea.dtos.exceptionDtos.ResponseException;
import com.exam.alsea.entity.Pais;
import com.exam.alsea.entity.Usuario;
import com.exam.alsea.repository.PaisRepository;
import com.exam.alsea.repository.UsuarioRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImple implements UsuarioServiceInterface {

    private final Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PaisRepository paisRepository;

    @Override
    public List<ResponseUsuario> getAllUsuarios() throws ResponseException {
        try {
            return usuarioRepository.findAll().stream().map(usuario ->
                    new ResponseUsuario(usuario.getIdUser(), usuario.getNombre(), usuario.getTelefono(),
                            usuario.getPais().getCodigo(), usuario.getMail(), usuario.getFechaRegistro(),
                            usuario.getFechaActualizacion())).collect(Collectors.toList());
        } catch (Exception exception) {
            throw new ResponseException(exception.getMessage(), "Intentando obtener todo los registros", null);
        }
    }

    @Override
    public ResponseUsuario getUsuarioById(Long id) throws ResponseException {
        try {
            Optional<Usuario> usuario = usuarioRepository.findById(id);

            if (Boolean.FALSE.equals(usuario.isPresent())) {
                throw new ResponseException("No se encontro el usuario con ese codigo, intenta con otro",
                        "No existe registro con ese id", null);
            }
            return new ResponseUsuario(usuario.get().getIdUser(), usuario.get().getNombre(), usuario.get().getTelefono(),
                    usuario.get().getPais().getCodigo(), usuario.get().getMail(), usuario.get().getFechaRegistro(),
                    usuario.get().getFechaActualizacion());
        } catch (ResponseException e) {
            throw e;
        }
    }

    @Override
    public Boolean modificarUsuario(Long id, RequestUpdateUsuario requestUsuario) throws ResponseException {
        try {
            HashMap response = requestUsuario.isValid();
            if (response.size() > 0) {
                if (response.containsKey("mailFormat")) {
                    throw new ResponseException("Body request invalid", "fields not valid", response);
                }
            }

            Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

            if (Boolean.FALSE.equals(usuarioOptional.isPresent())) {
                throw new ResponseException("No se encontro el usuario con ese codigo, intenta con otro",
                        "No existe registro con ese id", null);
            }

            Optional<Pais> pais = paisRepository.findByCodigo(usuarioOptional.get().getPais().getCodigo());

            LocalDateTime fechaUpdate = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of(pais.get().getUtc()))
                    .toLocalDateTime();

            usuarioOptional.get().setFechaActualizacion(fechaUpdate);
            if (Boolean.FALSE.equals(Objects.isNull(requestUsuario.getMail()) || requestUsuario.getMail().isEmpty())) {
                usuarioOptional.get().setMail(requestUsuario.getMail());
            }
            if (Boolean.FALSE.equals(Objects.isNull(requestUsuario.getNombre())
                    || requestUsuario.getNombre().isEmpty())) {
                usuarioOptional.get().setNombre(requestUsuario.getNombre());
            }
            if (Boolean.FALSE.equals(Objects.isNull(requestUsuario.getTelefono())
                    || requestUsuario.getTelefono().isEmpty())) {
                usuarioOptional.get().setTelefono(requestUsuario.getTelefono());
            }

            usuarioRepository.save(usuarioOptional.get());
            return true;
        } catch (ResponseException e) {
            throw e;
        }
    }

    @Override
    public ResponseUsuario saveUsuario(RequestUsuario requestUsuario) throws ResponseException {
        try {
            HashMap response = requestUsuario.isValid();
            if (response.size() > 0) {
                throw new ResponseException("Body request invalid", "fields not valid", response);
            }

            if (usuarioRepository.existsByMail(requestUsuario.getMail())) {
                throw new ResponseException("Este correo proporcionado ya existe", "ya existe el correo", null);
            }

            Optional<Pais> pais = paisRepository.findByCodigo(requestUsuario.getCodigoPais());

            if (Boolean.FALSE.equals(pais.isPresent())) {
                throw new ResponseException("No se encontro el pais con ese codigo, intenta con otro",
                        "no se encuentra en la base de datos", null);
            }

            LocalDateTime fechaRegistro = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of(pais.get().getUtc()))
                    .toLocalDateTime();

            Usuario usuario = new Usuario();
            usuario.setFechaRegistro(fechaRegistro);
            usuario.setMail(requestUsuario.getMail());
            usuario.setNombre(requestUsuario.getNombre());
            usuario.setPais(pais.get());
            usuario.setTelefono(requestUsuario.getTelefono());

            usuario = usuarioRepository.save(usuario);
            return new ResponseUsuario(usuario.getIdUser(), usuario.getNombre(), usuario.getTelefono(),
                    usuario.getPais().getCodigo(), usuario.getMail(), usuario.getFechaRegistro(),
                    usuario.getFechaActualizacion());
        } catch (ResponseException e) {
            throw e;
        }
    }

    @Override
    public Boolean deleteUsuario(Long id) throws ResponseException {
        try {
            Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

            if (Boolean.FALSE.equals(usuarioOptional.isPresent())) {
                throw new ResponseException("No se encontro el usuario con ese codigo, intenta con otro",
                        "No existe registro con ese id", null);
            }
            usuarioRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (ResponseException e) {
            throw e;
        }
    }
}
