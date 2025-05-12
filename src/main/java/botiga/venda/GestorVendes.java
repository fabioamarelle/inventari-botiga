package main.java.botiga.venda;

import main.java.botiga.producte.Producte;
import main.java.botiga.usuari.GestorUsuaris;
import main.java.botiga.usuari.Usuari;

import java.time.LocalDate;
import java.util.*;

public class GestorVendes {
    // La classe GestorVendes centralitza la gestió de l’historial de vendes, i també permet fer cerques o
    // generar informes bàsics. Podria ampliar-se amb funcionalitats com resum de vendes per període o vendes
    // per producte. Aquesta classe assegura la persistència temporal de les vendes realitzades dins de la sessió
    // i manté la coherència del sistema.

    private ArrayList<Venda> llista_vendes = new ArrayList<>();
    private GestorUsuaris gestorUsuaris;
    public GestorVendes() {
        this.llista_vendes = new ArrayList<>();
    }
    public void afegirVenda(Venda venda){
        llista_vendes.add(venda);
    }
    public void setGestorUsuaris(GestorUsuaris gestorUsuaris) {
        this.gestorUsuaris = gestorUsuaris;
    }
    public ArrayList<Venda> buscarVenda(String nom, LocalDate dia) {
        ArrayList<Venda> resultat = new ArrayList<>();
        boolean vendaTrobada = false;

        for (Usuari usuari : gestorUsuaris.getLlistaUsuaris()) {
            if (usuari.getNom().equals(nom)) {
                for (Venda venda : llista_vendes) {
                    if (venda.getData().equals(dia)) {
                        System.out.println(venda);
                        resultat.add(venda);
                        vendaTrobada = true;
                    }
                }
            }
        }

        if (!vendaTrobada) {
            System.out.println("No existeix aquesta venda.");
        }

        return resultat; //
    }
    public ArrayList<Venda> vendesPeriode(LocalDate dataInici, LocalDate dataFi){
        ArrayList<Venda> resultat = new ArrayList<>();
        for (Venda venda : llista_vendes){
            LocalDate dataVenda = venda.getData();
            if ((dataVenda.isEqual(dataInici) || dataVenda.isAfter(dataInici)) &&
                    (dataVenda.isEqual(dataFi) || dataVenda.isBefore(dataFi))) {
                resultat.add(venda);
            }

        }
        return resultat;
    }


    public int vendesProducte(Producte producte) {
        int totalVendes = 0;

        for (Venda venda : llista_vendes) {
            for (Transaccio transaccio : venda.getLlistaTransaccio()) {
                if (transaccio.getProducte().equals(producte)) {
                    totalVendes++;
                }
            }
        }

        return totalVendes;
    }

    public ArrayList<Venda> getLlista_vendes() {
        return llista_vendes;
    }
}
