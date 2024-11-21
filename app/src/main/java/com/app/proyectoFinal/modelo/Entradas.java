package com.app.proyectoFinal.modelo;

import java.sql.Timestamp;

public class Entradas {
    // Atributos
    private int idEntrada;
    private int idProducto;
    private int cantidad;
    private double costoUnitario;
    private double totalEntrada;
    private Timestamp fechaEntrada;

    public Entradas() {
        this.idEntrada = 0;
        this.idProducto = 0;
        this.cantidad = 0;
        this.costoUnitario = 0.0;
        this.totalEntrada = 0.0;
        this.fechaEntrada = Timestamp.valueOf("");
    }

        // Constructor con parámetros
    public Entradas(int idEntrada, int idProducto, int cantidad, double costoUnitario, double totalEntrada, Timestamp fechaEntrada) {
        this.idEntrada = idEntrada;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.costoUnitario = costoUnitario;
        this.totalEntrada = totalEntrada;
        this.fechaEntrada = fechaEntrada;
    }

    public int getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(int idEntrada) {
        this.idEntrada = idEntrada;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public double getTotalEntrada() {
        return totalEntrada;
    }

    public void setTotalEntrada(double totalEntrada) {
        this.totalEntrada = totalEntrada;
    }

    public Timestamp getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Timestamp fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    // Método para calcular el totalEntrada
    public void calcularTotalEntrada() {
        this.totalEntrada = this.cantidad * this.costoUnitario;
    }

    @Override
    public String toString() {
        return "Entradas{" +
                "idEntrada=" + idEntrada +
                ", idProducto=" + idProducto +
                ", cantidad=" + cantidad +
                ", costoUnitario=" + costoUnitario +
                ", totalEntrada=" + totalEntrada +
                ", fechaEntrada=" + fechaEntrada +
                '}';
    }
}
