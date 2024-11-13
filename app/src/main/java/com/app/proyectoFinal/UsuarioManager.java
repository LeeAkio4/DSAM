package com.app.proyectoFinal;

public class UsuarioManager {
    // Instancia única de la clase
    private static UsuarioManager instance;

    // Variable para almacenar el código del usuario
    private int codigoUsuario;

    // Constructor privado para evitar que se cree más de una instancia
    private UsuarioManager() { }

    // Método estático para obtener la instancia única de la clase
    public static UsuarioManager getInstance() {
        if (instance == null) {
            instance = new UsuarioManager();
        }
        return instance;
    }

    // Métodos para establecer y obtener el código de usuario
    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }
}
