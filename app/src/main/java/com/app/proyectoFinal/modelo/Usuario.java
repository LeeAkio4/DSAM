package com.app.proyectoFinal.modelo;

public class Usuario {

    public int codigo, dni;
    public String nombrescompletos, direccion, correo, genero, contrasena;

    public Usuario(){
        this.codigo = 0;
        this.nombrescompletos = "";
        this.direccion = "";
        this.dni = 0;
        this.genero = "";
        this.correo = "";
        this.contrasena = "";
    }

    public Usuario(int codigo, String nombrescompletos, String direccion, int dni, String genero, String correo, String contrasena) {
        this.codigo = codigo;
        this.nombrescompletos = nombrescompletos;
        this.direccion = direccion;
        this.dni = dni;
        this.genero = genero;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombrescompletos() {
        return nombrescompletos;
    }

    public void setNombrescompletos(String nombrescompletos) {
        this.nombrescompletos = nombrescompletos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "codigo" + codigo +
                ", nombres_completos" + nombrescompletos + '\n' +
                ", direccion" + direccion + '\n' +
                ", dni" + dni + '\n' +
                ", genero" + genero + '\n' +
                ", correo" + correo + '\n' +
                ", constraseña" + contrasena + '\n' +
                "}";
    }
}
