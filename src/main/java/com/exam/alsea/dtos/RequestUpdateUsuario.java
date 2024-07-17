package com.exam.alsea.dtos;

import com.exam.alsea.entity.enums.CodigoPais;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Pattern;

public class RequestUpdateUsuario implements Serializable {

    private String nombre;

    private String telefono;

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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public HashMap<String, String> isValid() {
        HashMap<String, String> validations = new HashMap<>();
        if (Boolean.FALSE.equals(Objects.isNull(mail) || mail.isEmpty())) {
            if (!Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$").matcher(mail).matches()) {
                validations.put("mailFormat", "Este campo no contiene el formato correcto example@domin.com");
            }
        }

        return validations;
    }

}
