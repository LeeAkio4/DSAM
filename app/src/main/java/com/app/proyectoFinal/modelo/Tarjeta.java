package com.app.proyectoFinal.modelo;

public class Tarjeta {

    public int codigo_t, codigo_u;
    public long numeroTar;
    public String nombre, correo, fechacad, cvv;

    public Tarjeta(){
        this.codigo_t = 0;
        this.codigo_u = 0;
        this.nombre = "";
        this.correo = "";
        this.numeroTar=0;
        this.fechacad = "";
        this.cvv = "";
    }

    public Tarjeta(int codigo_t, int codigo_u, String nombre, String correo, long numeroTar, String fechacad, String cvv) {
        this.codigo_t = codigo_t;
        this.codigo_u = codigo_u;
        this.nombre = nombre;
        this.correo = correo;
        this.numeroTar = numeroTar;
        this.fechacad = fechacad;
        this.cvv = cvv;
    }

    public int getCodigo_t() {
        return codigo_t;
    }

    public void setCodigo_t(int codigo_t) {
        this.codigo_t = codigo_t;
    }

    public int getCodigo_u() {
        return codigo_u;
    }

    public void setCodigo_u(int codigo_u) {
        this.codigo_u = codigo_u;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public long getNumeroTar() {return numeroTar; }

    public void setNumeroTar(long numeroTar) { this.numeroTar = numeroTar; }

    public String getFechacad() {
        return fechacad;
    }

    public void setFechacad(String fechacad) {
        this.fechacad = fechacad;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @Override
    public String toString() {
        return "Tarjeta{" +
                "codigo_tarjeta=" + codigo_t +
                ", codigo_usuario=" + codigo_u + '\n' +
                ", nombre='" + nombre + '\'' + '\n' +
                ", correo='" + correo + '\'' + '\n' +
                ", numero_tarjeta=" + numeroTar + '\n' +
                ", fecha_Caducidad='" + fechacad + '\'' + '\n' +
                ", cvv='" + cvv + '\'' + '\n' +
                '}';
    }

}
