package com.stif.loginapi.dto;

public class ApiResponse {

    private String mensaje;
    private Object datos;

    public ApiResponse() {
    }

    public ApiResponse(String mensaje, Object datos) {
        this.mensaje = mensaje;
        this.datos = datos;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Object getDatos() {
        return datos;
    }

    public void setDatos(Object datos) {
        this.datos = datos;
    }
}