package test.java.botiga.venda;

import main.java.botiga.usuari.Usuari;
import main.java.botiga.venda.GestorVendes;
import main.java.botiga.venda.Venda;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorVendesTest {
    // Ejemplo de test
    @Test
    public void testafegirVenda(){
        GestorVendes g = new GestorVendes();
        Usuari u = new Usuari("Yassine","yassine@gmail.com","Admin");
        Venda v = new Venda(LocalDate.of(2024,10,23),u);

        g.afegirVenda(v);

        assert g.getLlista_vendes().size() == 1;
    }
    @Test
    public void testvendesPeriode(){
        GestorVendes g = new GestorVendes();
        Usuari u = new Usuari("Yassine","yassine@gmail.com","Admin");
        Venda v = new Venda(LocalDate.of(2024,10,23),u);
        Venda v2 = new Venda(LocalDate.of(2024,10,24),u);
        Venda v3 = new Venda(LocalDate.of(2024,10,29),u);



        g.afegirVenda(v);
        g.afegirVenda(v2);
        g.afegirVenda(v3);

        ArrayList<Venda> resultat = g.vendesPeriode(LocalDate.of(2024,10,23),LocalDate.of(2024,10,25));

        assert resultat.size() == 2;

    }

    @Test
    public void testbuscarVenda(){

        GestorVendes g = new GestorVendes();
        Usuari u = new Usuari("Yassine","yassine@gmail.com","Admin");
        Venda v = new Venda(LocalDate.of(2024,10,23),u);
        Venda v2 = new Venda(LocalDate.of(2024,10,24),u);
        Venda v3 = new Venda(LocalDate.of(2024,10,29),u);



        g.afegirVenda(v);
        g.afegirVenda(v2);
        g.afegirVenda(v3);






    }

}
