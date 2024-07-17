package com.exam.alsea.dtos;

import com.exam.alsea.entity.enums.CodigoPais;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Pattern;

public class RequestUsuario implements Serializable {

    private String nombre;

    private String telefono;

    private CodigoPais codigoPais;

    private String mail;

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
