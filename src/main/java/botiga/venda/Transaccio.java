package main.java.botiga.venda;

import main.java.botiga.producte.Producte;

public class Transaccio {
    private Producte producte;
    private int quantitat;


    public Transaccio(Producte producte, int quantitat) {
        this.producte = producte;
        this.quantitat = quantitat;
    }

    public Producte getProducte() {
        return producte;
    }

    public void setProducte(Producte producte) {
        this.producte = producte;
    }

    public int getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }

    @Override
    public String toString() {
        return producte.getNom() + " (Quantitat: " + quantitat + ")";
    }
}
