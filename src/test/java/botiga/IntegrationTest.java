package test.java.botiga;

import main.java.botiga.Botiga;
import main.java.botiga.producte.Producte;
import main.java.botiga.usuari.Rol;
import main.java.botiga.usuari.Usuari;
import main.java.botiga.venda.Venda;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class IntegrationTest {
    @Test
    // Test per a les funcionalitats d'afegir i buscar Usuari, Producte i Venda
    public void testAfegirIBuscarUsuariProducteVenda() {
        Usuari usuari = new Usuari("Yassine", "yassine@gmail.com", Rol.CLIENT);
        Botiga.gestorUsuaris.afegirUsuari(usuari);

        Producte producte = new Producte("Aigua", 1.99, 20);
        Botiga.gestorProductes.afegirProducte(producte);

        Venda venda = new Venda(LocalDate.of(2025, 5, 13), usuari);
        Botiga.gestorVendes.afegirVenda(venda);

        assert (usuari == Botiga.gestorUsuaris.obtenirUsuari(usuari.getCorreuElectronic()));
        assert (producte == Botiga.gestorProductes.cercarProducte(producte.getNom()));
        assert (venda == Botiga.gestorVendes.buscarVenda(venda.getUsuari().getCorreuElectronic(), venda.getData()).get(0));
    }

    // Test per a les funcionalitats d'esborrar Usuari, Producte i Venda
    @Test
    public void testEsborrarUsuariProducteVenda() {
        Usuari usuari = new Usuari("Yassine", "yassine@gmail.com", Rol.CLIENT);
        Botiga.gestorUsuaris.afegirUsuari(usuari);
        assert (usuari == Botiga.gestorUsuaris.obtenirUsuari(usuari.getCorreuElectronic()));

        Producte producte = new Producte("Aigua", 1.99, 20);
        Botiga.gestorProductes.afegirProducte(producte);
        assert (producte == Botiga.gestorProductes.cercarProducte(producte.getNom()));

        Venda venda = new Venda(LocalDate.of(2025, 5, 13), usuari);
        Botiga.gestorVendes.afegirVenda(venda);
        assert (venda == Botiga.gestorVendes.buscarVenda(venda.getUsuari().getCorreuElectronic(), venda.getData()).get(0));

        Botiga.gestorUsuaris.esborrarUsuari(usuari.getCorreuElectronic());
        Botiga.gestorProductes.eliminarProducte(producte.getNom());
        Botiga.gestorVendes.esborrarVenda(venda);

        assert (!Botiga.gestorUsuaris.getTotsUsuaris().contains(usuari));
        assert (!Botiga.gestorProductes.getCataleg().contains(producte));
        assert (!Botiga.gestorVendes.getLlistaVendes().contains(venda));
    }

    // Test per a les funcionalitats de llistar Usuari, Producte i Venda
    @Test
    public void testLlistarUsuariProducteVenda(){
        Usuari usuari = new Usuari("Yassine", "yassine@gmail.com", Rol.CLIENT);
        Botiga.gestorUsuaris.afegirUsuari(usuari);

        Producte producte = new Producte("Aigua", 1.99, 20);
        Botiga.gestorProductes.afegirProducte(producte);

        Venda venda = new Venda(LocalDate.of(2025, 5, 13), usuari);
        Botiga.gestorVendes.afegirVenda(venda);

        assert (usuari == Botiga.gestorUsuaris.getTotsUsuaris().get(0));
        assert (producte == Botiga.gestorProductes.getCataleg().get(0));
        assert (venda == Botiga.gestorVendes.getLlistaVendes().get(0));
    }
}

