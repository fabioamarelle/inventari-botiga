package main.java.botiga.producte;

import main.java.botiga.utilitats.InputHelper;

import java.util.ArrayList;

public class GestorProductes {
    //llista per gestionar productes
    private final ArrayList<Producte> cataleg;
    public GestorProductes() {
        this.cataleg = new ArrayList<>();
    }

    //afegir productes
    public void afegirProducte(Producte p) {
        cataleg.add(p);
    }
    //afegir productes amb input
    public void afegirProducte() {
        String nom = InputHelper.llegirString("Introduexi el nom del producte:");
        double preu = InputHelper.llegirDecimal("Introduexi el preu del producte:");
        int stock = InputHelper.llegirEnterPositiu("Introduexi el stock del producte:");
        cataleg.add(new Producte(nom, preu, stock));
    }


    //cerca de producte per nom
    public ArrayList<Producte> cercarPerNom(String nomParcial) {
        ArrayList<Producte> resultats = new ArrayList<>();

        for (Producte p : cataleg) {

            String nomDelProducte = p.getNom();
            String nomProducteMinuscules = nomDelProducte.toLowerCase();
            String nomParcialMinuscules = nomParcial.toLowerCase();

            if (nomProducteMinuscules.contains(nomParcialMinuscules)) {
                resultats.add(p);
            }
        }
        return resultats;
    }

    //eliminar producte per nom
    public boolean eliminarProducte(String nom) {
        for (Producte p : cataleg) {
            String nomProducte = p.getNom();
            String nomProducteMinuscules = nomProducte.toLowerCase();
            String nomABuscarMinuscules = nom.toLowerCase();

            if (nomProducteMinuscules.equals(nomABuscarMinuscules)) {
                cataleg.remove(p);
                return true;
            }
        }
        return false;
    }

    //aplicar descuento al producto
    public void aplicarDescompte(String nom ,double percentatge) {
        ArrayList<Producte> resultats = cercarPerNom(nom);
        for (Producte p : resultats) {
            double preuActual = p.getPreu();
            double descompte = percentatge / 100.0;
            double nouPreu = preuActual * (1 - descompte);
            double preuArrodonit = Math.round(nouPreu * 100.0) / 100.0;
            p.setPreu(preuArrodonit);
        }
    }

    //mostrar el catalogo de productos
    public void mostrarCataleg() {
        for (Producte p : cataleg) {
            System.out.println(p);
        }
    }
}