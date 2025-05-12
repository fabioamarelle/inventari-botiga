package test.java.botiga.venda;

import main.java.botiga.producte.Producte;
import main.java.botiga.usuari.Usuari;
import main.java.botiga.venda.Transaccio;
import main.java.botiga.venda.Venda;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class VendaTest {
    @Test
    public void getDataOK(){
        // ARRANGE
        Venda venda = new Venda(LocalDate.of(2006,4,30));

        // ACT
        LocalDate result = venda.getData();

        // ASSERT
        assert (Objects.equals(result, LocalDate.of(2006, 4, 30)));
    }

    @Test
    public void setDataOK(){
        // ARRANGE
        Venda venda = new Venda(LocalDate.of(2006,4,30));

        // ACT
        venda.setData(LocalDate.of(2007,4,30));
        LocalDate result = venda.getData();

        // ASSERT
        assert (Objects.equals(result, LocalDate.of(2007, 4, 30)));
    }

    @Test
    public void getUsuariOK(){
        // ARRANGE
        Usuari usuari = new Usuari("Aitor", "treball@gmail.com","Administrador");
        Venda venda = new Venda(LocalDate.of(2006,4,30), usuari);

        // ACT
        Usuari result = venda.getUsuari();

        // ASSERT
        assert (result == usuari);
    }

    @Test
    public void setUsuariOK(){
        // ARRANGE
        Usuari usuari1 = new Usuari("Aitor", "treball@gmail.com","Administrador");
        Usuari usuari2 = new Usuari("Yassine", "notreball@gmail.com", "Client");
        Venda venda = new Venda(LocalDate.of(2006,4,30), usuari1);

        // ACT
        venda.setUsuari(usuari2);
        Usuari result = venda.getUsuari();

        // ASSERT
        assert (result == usuari2);
    }

    @Test
    public void getLlistaTransaccio(){
        // ARRANGE
        ArrayList<Transaccio> llistaTransaccio = new ArrayList<>();

        Venda venda = new Venda(LocalDate.of(2006,4,30));

        // ACT
        ArrayList<Transaccio> result = venda.getLlistaTransaccio();

        // ASSERT
        assert (result.equals(llistaTransaccio));
    }

    @Test
    public void setLlistaTransaccio(){
        // ARRANGE
        ArrayList<Transaccio> llistaTransaccio = new ArrayList<>();
        Venda venda = new Venda(LocalDate.of(2006,4,30));
        Usuari client = new Usuari("Felipe", "felipe@gmail.com", "client");
        Producte producte = new Producte("Ratolí", 10, 25);
        Transaccio transaccio1 = new Transaccio(producte, 3, client);
        Transaccio transaccio2 = new Transaccio(producte, 6, client);

        llistaTransaccio.add(transaccio1);
        venda.afegirTransaccio(transaccio2);

        // ACT
        venda.setLlistaTransaccio(llistaTransaccio);
        ArrayList<Transaccio> result = venda.getLlistaTransaccio();

        // ASSERT
        assert (result == llistaTransaccio);
    }

    @Test
    public void esborrarTransaccio(){
        // ARRANGE
        Venda venda = new Venda(LocalDate.of(2006,4,30));
        Usuari client = new Usuari("Felipe", "felipe@gmail.com", "client");
        Producte producte = new Producte("Ratolí", 10, 25);
        Transaccio transaccio1 = new Transaccio(producte, 3, client);
        Transaccio transaccio2 = new Transaccio(producte, 6, client);

        // ACT
        venda.afegirTransaccio(transaccio1);
        venda.afegirTransaccio(transaccio2);
        venda.esborrarTransaccio(transaccio1);

        // ASSERT
        assert venda.getLlistaTransaccio().size() == 1;
    }
}
