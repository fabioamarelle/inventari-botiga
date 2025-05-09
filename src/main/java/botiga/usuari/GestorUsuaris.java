package main.java.botiga.usuari;
import java.util.ArrayList;
import java.util.List;

public class GestorUsuaris {
    // La classe GestorUsuaris s’encarrega de gestionar la llista d’usuaris registrats i operar-hi: afegir nous usuaris,
    // validar-ne l’existència, obtenir un usuari concret... Aquesta capa, igual que el GestorProductes, separa la lògica d’interacció
    // amb els usuaris del codi de la interfície o del flux principal del programa.

    private List<Usuari> llistaUsuaris;

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

    public List<Usuari> getTotsUsuaris() {
        return new ArrayList<>(llistaUsuaris);
    }

    public List<Usuari> getLlistaUsuaris() {
        return llistaUsuaris;
    }

    public void setLlistaUsuaris(List<Usuari> llistaUsuaris) {
        this.llistaUsuaris = llistaUsuaris;

    }

}

