package main.java.botiga.usuari;

public class Usuari {
    // crem els 3 atributs
    private String nom;
    private String correuElectronic;
    private String rol; // "administrador" o "client"
    // Generem get/set i construcutor
    public Usuari(String nom, String correuElectronic, String rol) {
        this.nom = nom;
        this.correuElectronic = correuElectronic;
        this.rol = rol;
    }

    public String getNom() {
        return nom;
    }

    public String getCorreuElectronic() {
        return correuElectronic;
    }

    public String getRol() {
        return rol;
    }

    public boolean esAdministrador() {
        return "administrador".equalsIgnoreCase(rol);
    }
    //hola
}

