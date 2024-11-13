package com.app.proyectoFinal.modelo;

public class Producto {

    public int codigo_prod, stock, cilindros;
    public String nombre, marca, descripcion, año, color, transmision, tipomotor, placa;
    public double precio;

    public Producto(){
        this.codigo_prod = 0;
        this.nombre = "";
        this.marca = "";
        this.descripcion = "";
        this.precio = 0;
        this.stock = 0;
        this.año = "";
        this.color = "";
        this.cilindros = 0;
        this.transmision ="";
        this.tipomotor = "";
        this.placa ="";
    }

    public Producto(int codigo_prod, int stock, int cilindros, String nombre, String marca, String descripcion, String año, String color, String transmision, String tipomotor, String placa, double precio) {
        this.codigo_prod = codigo_prod;
        this.stock = stock;
        this.cilindros = cilindros;
        this.nombre = nombre;
        this.marca = marca;
        this.descripcion = descripcion;
        this.año = año;
        this.color = color;
        this.transmision = transmision;
        this.tipomotor = tipomotor;
        this.placa = placa;
        this.precio = precio;
    }

    public int getCodigo_prod() {
        return codigo_prod;
    }

    public void setCodigo_prod(int codigo_prod) {
        this.codigo_prod = codigo_prod;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCilindros() {
        return cilindros;
    }

    public void setCilindros(int cilindros) {
        this.cilindros = cilindros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    public String getTipomotor() {
        return tipomotor;
    }

    public void setTipomotor(String tipomotor) {
        this.tipomotor = tipomotor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "codigo_producto" + codigo_prod +
                ", nombre" + nombre + '\n' +
                ", marca" + marca + '\n' +
                ", descripcion" + descripcion + '\n' +
                ", precio" + precio + '\n' +
                ", stock" + stock + '\n' +
                ", año" + año +
                ", color" + color + '\n' +
                ", cilindros" + cilindros + '\n' +
                ", transmision" + transmision + '\n' +
                ", tipomotor" + tipomotor + '\n' +
                ", placa" + placa + '\n' +
                "}";
    }
}
