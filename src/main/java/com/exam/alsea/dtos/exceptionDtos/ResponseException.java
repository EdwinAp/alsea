package com.exam.alsea.dtos.exceptionDtos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public class ResponseException extends Exception implements Serializable {

    private String message;
    private String cause;
    private HashMap details;

    public ResponseException(String message, String cause, HashMap details) {
        this.cause = cause;
        this.message = message;
        this.details = details;
    }

    public HashMap<String, Object> getResponse() {
        HashMap<String, Object> response = new HashMap();
        if (Boolean.FALSE.equals(Objects.isNull(message) || message.isEmpty())) {
            response.put("message", this.message);
        }
        if (Boolean.FALSE.equals(Objects.isNull(cause) || cause.isEmpty())) {
            response.put("cause", this.cause);
        }
        if (Boolean.FALSE.equals(Objects.isNull(details) || details.isEmpty())) {
            response.put("details", details);
        }
        return response;
    }

}
