package main.java.botiga.usuari;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


class GestorUsuariTest {

    private GestorUsuaris gestor;
    private Usuari usuari1;
    private Usuari usuari2;

    @BeforeEach
    void configurar() {
        gestor = new GestorUsuaris();
        usuari1 = new Usuari("Alice", "alice@example.com");
        usuari2 = new Usuari("Bob",   "bob@example.com");
    }

    @Test
    void afegirUsuari_retornaCert_quanNou() {
        boolean resultat = gestor.afegirUsuari(usuari1);
        assertTrue(resultat);
        assertTrue(gestor.existeixUsuari("alice@example.com"));
    }

    @Test
    void afegirUsuari_retornaFals_quanJaExisteix() {
        gestor.afegirUsuari(usuari1);
        boolean resultat = gestor.afegirUsuari(new Usuari("Alice Dup", "alice@example.com"));
        assertFalse(resultat);
    }

    @Test
    void existeixUsuari_indiferentMajusculesMinuscules() {
        gestor.afegirUsuari(usuari1);
        assertTrue(gestor.existeixUsuari("ALICE@EXAMPLE.COM"));
    }

    @Test
    void obtenirUsuari_retornaUsuari_quanExisteix() {
        gestor.afegirUsuari(usuari2);
        Usuari trobat = gestor.obtenirUsuari("bob@example.com");
        assertNotNull(trobat);
        assertEquals("Bob", trobat.getNom());
    }

    @Test
    void obtenirUsuari_retornaNull_quanNoExisteix() {
        Usuari trobat = gestor.obtenirUsuari("noexisteix@example.com");
        assertNull(trobat);
    }

    @Test
    void getTotsUsuaris_retornaTotsElsAfegits() {
        gestor.afegirUsuari(usuari1);
        gestor.afegirUsuari(usuari2);
        List<Usuari> tots = gestor.getTotsUsuaris();
        assertEquals(2, tots.size());
        assertTrue(tots.contains(usuari1));
        assertTrue(tots.contains(usuari2));
    }
}
