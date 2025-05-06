package test.java.botiga.usuari;
import main.java.botiga.usuari.Usuari;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
//Usuari test
public class UsuariTest {

    @Test
    public void testConstructorIGetters() {
        //verifiquem que els constructors , i els get and sett
        Usuari usuari = new Usuari("Arnau", "arnau@gmail.com", Rol.CLIENT);

        assertEquals("Arnau", usuari.getNom());
        assertEquals("arnau@gmail.com", usuari.getCorreuElectronic());
        assertEquals("client", usuari.getRol());
    }

    @Test
    public void testEsAdministradorAmbAdministrador() {
        // crem els Usuaris i verifquem
        Usuari usuariAdmin = new Usuari("joel", "joel@gmail.com", Rol.ADMINISTRADOR);

        assertTrue(usuariAdmin.esAdministrador());
    }

    @Test
    public void testEsAdministradorAmbClient() {
        Usuari usuariClient = new Usuari("edgar", "edgar@gmail.com", Rol.CLIENT);

        assertFalse(usuariClient.esAdministrador());
    }
}
