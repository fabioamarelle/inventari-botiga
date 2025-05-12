package main.java.botiga.usuari;
import main.java.botiga.utilitats.InputHelper;

import java.util.ArrayList;

public class GestorUsuaris {
    // La classe GestorUsuaris s’encarrega de gestionar la llista d’usuaris registrats i operar-hi: afegir nous usuaris,
    // validar-ne l’existència, obtenir un usuari concret... Aquesta capa, igual que el GestorProductes, separa la lògica d’interacció
    // amb els usuaris del codi de la interfície o del flux principal del programa.

    private ArrayList<Usuari> llistaUsuaris;

    public GestorUsuaris() {
        this.llistaUsuaris = new ArrayList<>();
    }

    public boolean afegirUsuari(Usuari usuari) {
        if (existeixUsuari(usuari.getCorreuElectronic())) {
            return false;
        }
        llistaUsuaris.add(usuari);
        return true;
    }

    public void afegirUsuari() {
        String nom = InputHelper.llegirString("Introdueix el nom: ");
        String correuElectronic = InputHelper.llegirString("Introdueix el correu electrònic: ");
        boolean esAdministrador = InputHelper.llegirBoolean("Introdueix el rol: ", "Administrador", "Client");

        if (esAdministrador){
            Rol rol = Rol.ADMINISTRADOR;
            llistaUsuaris.add(new Usuari(nom, correuElectronic, rol));
        } else {
            Rol rol = Rol.CLIENT;
            llistaUsuaris.add(new Usuari(nom, correuElectronic, rol));
        }
        System.out.println("Usuari afegit.");

    }

    public boolean existeixUsuari(String correuElectronic) {
        for (Usuari u : llistaUsuaris) {
            if (u.getCorreuElectronic().equalsIgnoreCase(correuElectronic)) {
                return true;
            }
        }
        return false;
    }

    public Usuari obtenirUsuari(String correuElectronic) {
        for (Usuari u : llistaUsuaris) {
            if (u.getCorreuElectronic().equalsIgnoreCase(correuElectronic)) {
                return u;
            }
        }
        return null;
    }

    public Usuari obtenirUsuari() {
        String correuElectronic = InputHelper.llegirString("Introdueix el correu electrònic de l'usuari a buscar: ");
        for (Usuari u : llistaUsuaris) {
            if (u.getCorreuElectronic().equalsIgnoreCase(correuElectronic)) {
                return u;
            }
        }
        return null;
    }

    public ArrayList<Usuari> getTotsUsuaris() {
        return new ArrayList<>(llistaUsuaris);
    }

    public ArrayList<Usuari> getLlistaUsuaris() {
        return llistaUsuaris;
    }

    public void setLlistaUsuaris(ArrayList<Usuari> llistaUsuaris) {
        this.llistaUsuaris = llistaUsuaris;
    }

    public void esborrarUsuari(String correuElectronic) {
        llistaUsuaris.remove(obtenirUsuari(correuElectronic));
    }

    public void esborrarUsuari() {
        String correuElectronic = InputHelper.llegirString("Introdueix el correu electrònic de l'usuari a esborrar: ");
        llistaUsuaris.remove(obtenirUsuari(correuElectronic));
    }
}
