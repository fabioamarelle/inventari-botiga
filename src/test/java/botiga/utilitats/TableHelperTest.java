package test.java.botiga.utilitats;

import main.java.botiga.utilitats.TableHelper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class TableHelperTest {
    @Test
    public void testLlistaATaula() {
        ArrayList<String> llista = new ArrayList<>();
        llista.add("Element 1");
        llista.add("Element 2");

        String taula = TableHelper.llistaATaula("Títol", llista);
        assert (taula.contains("Element 1"));
        assert (taula.contains("Element 2"));
    }
    @Test
    public void testHashMapATaula() {
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1, "Element 1");
        hashMap.put(2, "Element 2");

        String taula = TableHelper.hashMapATaula("Clau", "Valor", hashMap);
        assert (taula.contains("Element 1"));
        assert (taula.contains("Element 2"));
    }
}
