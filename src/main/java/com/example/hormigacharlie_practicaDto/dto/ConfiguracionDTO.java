package com.example.hormigacharlie_practicaDto.dto;

//Representa la configuración inicial de la simulación de Charlie.
//Responde a la pregunta: ¿Qué configuración pidió el usuario?
public class ConfiguracionDTO {

    private int dimension;

    //X representa la fila y Y representa la columna.
    private int posicionX;
    private int posicionY;

    private String direccion;

    private int cantidadFotos;

    private int ampliacion;


    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCantidadFotos() {
        return cantidadFotos;
    }

    public void setCantidadFotos(int cantidadFotos) {
        this.cantidadFotos = cantidadFotos;
    }

    public int getAmpliacion() {
        return ampliacion;
    }

    public void setAmpliacion(int ampliacion) {
        this.ampliacion = ampliacion;
    }
}