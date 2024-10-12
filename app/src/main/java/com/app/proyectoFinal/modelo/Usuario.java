package com.app.proyectoFinal.modelo;

public class Usuario {

    public int codigo;
    public String nombre, apellido, celular, correo, contrasena;

    public Usuario(){
        this.codigo = 0;
        this.nombre = "";
        this.apellido = "";
        this.celular = "";
        this.correo = "";
        this.contrasena = "";
    }

    public Usuario(int codigo, String nombre, String apellido, String celular, String correo, String contrasena) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "codigo" + codigo +
                ", nombre" + nombre + '\n' +
                ", apellido" + apellido + '\n' +
                ", celular" + celular + '\n' +
                ", correo" + correo + '\n' +
                ", constraseña" + contrasena + '\n' +
                "}";
    }
}
