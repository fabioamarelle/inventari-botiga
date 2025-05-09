package main.java.botiga.usuari;


//usuari
public class Usuari {
    private String nom;
    private String correuElectronic;
    private Rol rol;

    public Usuari(String nom, String correuElectronic, Rol rol) {
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

    public Rol getRol() {
        return rol;
    }

    public boolean esAdministrador() {
        return rol == Rol.ADMINISTRADOR;
    }
}
