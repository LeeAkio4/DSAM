package com.app.proyectoFinal.modelo;

public class Producto {

    public int codigo_prod;
    public String nombre, marca, descripcion, precio, stock;

    public Producto(){
        this.codigo_prod = 0;
        this.nombre = "";
        this.marca = "";
        this.descripcion = "";
        this.precio = "";
        this.stock="";
    }

    public Producto(int codigo_prod, String nombre, String marca, String descripcion, String precio, String stock) {
        this.codigo_prod = codigo_prod;
        this.nombre = nombre;
        this.marca = marca;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock=stock;
    }

    public int getCodigo_prod() {
        return codigo_prod;
    }

    public void setCodigo_prod(int codigo_prod) {
        this.codigo_prod = codigo_prod;
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

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
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
                "}";
    }
}
