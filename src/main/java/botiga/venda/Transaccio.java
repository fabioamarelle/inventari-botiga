package main.java.botiga.venda;

import main.java.botiga.producte.Producte;
import main.java.botiga.usuari.Usuari;


public class Transaccio {
    private Producte producte;
    private int quantitat;

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

    public Transaccio(Producte producte, int quantitat) {
        this.producte = producte;
        this.quantitat = quantitat;
    }
}
