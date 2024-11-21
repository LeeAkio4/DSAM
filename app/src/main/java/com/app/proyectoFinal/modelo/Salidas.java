package com.app.proyectoFinal.modelo;

import java.sql.Timestamp;

public class Salidas {
    private int idSalida;
    private int idPedido;
    private int cantidad;
    private double precioUnitario;
    private double totalSalida;
    private Timestamp fechaSalida;

    public Salidas() {
        this.idSalida = 0;
        this.idPedido = 0;
        this.cantidad = 0;
        this.precioUnitario = 0.0;
        this.totalSalida = 0.0;
        this.fechaSalida = Timestamp.valueOf("");
    }

    public Salidas(int idSalida, int idPedido, int cantidad, double precioUnitario, double totalSalida, Timestamp fechaSalida) {
        this.idSalida = idSalida;
        this.idPedido = idPedido;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.totalSalida = totalSalida;
        this.fechaSalida = fechaSalida;
    }

    public int getIdSalida() {
        return idSalida;
    }

    public void setIdSalida(int idSalida) {
        this.idSalida = idSalida;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getTotalSalida() {
        return totalSalida;
    }

    public void setTotalSalida(double totalSalida) {
        this.totalSalida = totalSalida;
    }

    public Timestamp getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Timestamp fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    // Método para calcular el totalSalida
    public void calcularTotalSalida() {
        this.totalSalida = this.cantidad * this.precioUnitario;
    }

    @Override
    public String toString() {
        return "Salidas{" +
                "idSalida=" + idSalida +
                ", idPedido=" + idPedido +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", totalSalida=" + totalSalida +
                ", fechaSalida=" + fechaSalida +
                '}';
    }
}
