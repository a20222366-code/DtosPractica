package com.example.hormigacharlie_practicaDto.dto;


//Representa cómo quedó Charlie después de ejecutar la simulación.
//Responde a la pregunta: ¿Cómo está Charlie en un momento determinado?
public class EstadoDTO {

    //X representa la fila y Y representa la columna.
    private int posicionX;
    private int posicionY;

    private String direccion;

    private int movimientos;

    private int dimension;

    //Matriz bidimensional de enteros que representa las posiciones del terreno
    private int[][] terreno;




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

    public int getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(int movimientos) {
        this.movimientos = movimientos;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public int[][] getTerreno() {
        return terreno;
    }

    public void setTerreno(int[][] terreno) {
        this.terreno = terreno;
    }
}
