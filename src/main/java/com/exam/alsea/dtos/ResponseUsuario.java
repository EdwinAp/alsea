package com.exam.alsea.dtos;

import com.exam.alsea.entity.enums.CodigoPais;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Pattern;

public class ResponseUsuario implements Serializable {

    private Long idUser;

    private String nombre;

    private String telefono;

    private CodigoPais codigoPais;

    private String mail;

    private LocalDateTime fechaRegistro;

    private LocalDateTime fechaActualizacion;

    public ResponseUsuario(Long idUser, String nombre, String telefono, CodigoPais codigoPais, String mail,
                           LocalDateTime fechaRegistro, LocalDateTime fechaActualizacion) {
        this.idUser = idUser;
        this.nombre = nombre;
        this.telefono = telefono;
        this.codigoPais = codigoPais;
        this.mail = mail;
        this.fechaRegistro = fechaRegistro;
        this.fechaActualizacion = fechaActualizacion;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public CodigoPais getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(CodigoPais codigoPais) {
        this.codigoPais = codigoPais;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public HashMap<String, String> isValid() {
        final String cause = "Esta campo debe es obligatorio y no puede ser enviado vacio o nulo";
        HashMap<String, String> validations = new HashMap<>();
        if (Objects.isNull(nombre) || nombre.isEmpty()) {
            validations.put("nombre", cause);
        }
        if (Objects.isNull(telefono) || telefono.isEmpty()) {
            validations.put("telefono", cause);
        }
        if (Objects.isNull(mail) || mail.isEmpty()) {
            validations.put("mail", cause);
        }
        if (!Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$").matcher(mail).matches()) {
            validations.put("mailFormat", "Este campo no contiene el formato correcto example@domin.com");
        }
        return validations;
    }

}
