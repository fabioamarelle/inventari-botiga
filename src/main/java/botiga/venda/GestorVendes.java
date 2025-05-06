package main.java.botiga.venda;

import main.java.botiga.producte.Producte;

import java.time.LocalDate;
import java.util.*;

public class GestorVendes {
    // La classe GestorVendes centralitza la gestió de l’historial de vendes, i també permet fer cerques o
    // generar informes bàsics. Podria ampliar-se amb funcionalitats com resum de vendes per període o vendes
    // per producte. Aquesta classe assegura la persistència temporal de les vendes realitzades dins de la sessió
    // i manté la coherència del sistema.

    private ArrayList<Venda> llista_venda = new ArrayList<>();

    public GestorVendes() {
        this.llista_vendes = new ArrayList<>();
    }
    public void afegirVenda(Venda venda){
        llista_vendes.add(venda);
    }
    public void buscarVenda(Venda venda){
        for (Venda venda2 : llista_venda){
            if (venda2.equals(venda)){
                System.out.println(venda);
            }
            else {
                System.out.println("No existeix aquesta venda.");
            }
        }

    }
    public void vendesPeriode(LocalDate dataInici, LocalDate dataFi){
        for (Venda venda : llista_venda){
            LocalDate dataVenda = venda.getData();
            if ((dataVenda.isEqual(dataInici) || dataVenda.isAfter(dataInici)) &&
                    (dataVenda.isEqual(dataFi) || dataVenda.isBefore(dataFi))) {
                System.out.println(venda);
            }
        }
    }


    public void vendesProducte(Producte producte){
        HashMap<String,Integer> producteQuantitat = new HashMap<>();
        for (Venda venda : llista_venda)  {
            for (Transaccio transaccio: )
        }

    }
}
