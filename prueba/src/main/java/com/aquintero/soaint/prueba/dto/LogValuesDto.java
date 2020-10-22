package com.aquintero.soaint.prueba.dto;

public class LogValuesDto
{

    private String message;
    private String tipo;

    public LogValuesDto() {
    }

    public LogValuesDto(String message, String tipo)
    {
        this.message = message;
        this.tipo = tipo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
