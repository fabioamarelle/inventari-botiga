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

        // Aqui vaig afegint els nous usuaris si no exixteixen
        public boolean afegirUsuari(Usuari usuari) {
            if (existeixUsuari(usuari.getCorreuElectronic())) {
                return false;
            }
            llistaUsuaris.add(usuari);
            return true;
        }

        // Comprova si existeix un usuari amb el correu electrònic donat
        public boolean existeixUsuari(String correuElectronic) {
            for (Usuari u : llistaUsuaris) {
                if (u.getCorreuElectronic().equalsIgnoreCase(correuElectronic)) {
                    return true;
                }
            }
            return false;
        }

        // Retorna l'usuari amb el correu electrònic donat, o null si no existeix
        public Usuari obtenirUsuari(String correuElectronic) {
            for (Usuari u : llistaUsuaris) {
                if (u.getCorreuElectronic().equalsIgnoreCase(correuElectronic)) {
                    return u;
                }
            }
            return null;
        }

        // Retorna la llista completa d'usuaris
        public List<Usuari> getTotsUsuaris() {
            return new ArrayList<>(llistaUsuaris);
        }
    }
