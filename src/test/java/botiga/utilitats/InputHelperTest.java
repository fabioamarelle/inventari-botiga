package test.java.botiga.utilitats;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import static main.java.botiga.utilitats.InputHelper.*;

public class InputHelperTest {

    private InputStream copiaSystemIn;

    @BeforeEach
    public void copiarSystemIn() {
        copiaSystemIn = System.in;
    }

    @AfterEach
    public void restaurarSystemIn() {
        System.setIn(copiaSystemIn);
    }

    private void setInputStream(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    @Test
    public void LlegirEnterOK() throws InterruptedException {
        String input = "123\n";
        setInputStream(input);
        int result = llegirEnter("Introdueix un número enter");
        assert 123 == result;
    }

    @Test
    public void LlegirEnterInvalidOK() throws InterruptedException {
        String input = "abc\n456\n";
        setInputStream(input);
        int result = llegirEnter("Introdueix un número enter");
        assert 456 == result;
    }

    @Test
    public void LlegirEnterNegatiuOK() throws InterruptedException {
        String input = "-123\n";
        setInputStream(input);
        int result = llegirEnter("Introdueix un número enter");
        assert -123 == result;
    }

    @Test
    public void LlegirEnterPositiuOK() throws InterruptedException {
        String input = "123\n";
        setInputStream(input);
        int result = llegirEnterPositiu("Introdueix un número enter positiu");
        assert 123 == result;
    }

    @Test
    public void LlegirEnterPositiuInvalidOK() throws InterruptedException {
        String input = "abc\n456\n";
        setInputStream(input);
        int result = llegirEnterPositiu("Introdueix un número enter positiu");
        assert 456 == result;
    }

    @Test
    public void LlegirEnterPositiuNegatiuOK() throws InterruptedException {
        String input = "-123\n0\n456\n";
        setInputStream(input);
        int result = llegirEnterPositiu("Introdueix un número enter positiu");
        TimeUnit.MILLISECONDS.sleep(100);
        assert 456 == result;
    }

    @Test
    public void LlegirDecimalOK() throws InterruptedException {
        String input = "123.4\n";
        setInputStream(input);
        double result = llegirDecimal("Introdueix un número decimal");
        TimeUnit.MILLISECONDS.sleep(100);
        assert 123.4 == result;
    }

    @Test
    public void LlegirDecimalInvalidOK() throws InterruptedException {
        String input = "abc\n456.7\n";
        setInputStream(input);
        double result = llegirDecimal("Introdueix un número decimal");
        TimeUnit.MILLISECONDS.sleep(100);
        assert 456.7 == result;
    }

    @Test
    public void LlegirDecimalNegatiuOK() throws InterruptedException {
        String input = "-123.4\n";
        setInputStream(input);
        double result = llegirDecimal("Introdueix un número decimal");
        TimeUnit.MILLISECONDS.sleep(100);
        assert -123.4 == result;
    }

    @Test
    public void LlegirStringOK() throws InterruptedException {
        String input = "abc\n";
        setInputStream(input);
        String result = llegirString("Introdueix un text");
        TimeUnit.MILLISECONDS.sleep(100);
        assert "abc".equals(result);
    }

    @Test
    public void LlegirStringNumeroOK() throws InterruptedException {
        String input = "123\n";
        setInputStream(input);
        String result = llegirString("Introdueix un text");
        TimeUnit.MILLISECONDS.sleep(100);
        assert "123".equals(result);
    }

    @Test
    public void LlegirStringInvalidOK() throws InterruptedException {
        String input = "\nabc\n";
        setInputStream(input);
        String result = llegirString("Introdueix un text");
        TimeUnit.MILLISECONDS.sleep(100);
        assert "abc".equals(result);
    }

    @Test
    public void LlegirBooleanTrueOK() throws InterruptedException {
        String input = "si\n";
        setInputStream(input);
        boolean result = llegirBoolean("Selecciona una opció: ", "si", "no");
        TimeUnit.MILLISECONDS.sleep(100);
        assert result;
    }

    @Test
    public void LlegirBooleanFalseOK() throws InterruptedException {
        String input = "no\n";
        setInputStream(input);
        boolean result = llegirBoolean("Selecciona una opció: ", "si", "no");
        TimeUnit.MILLISECONDS.sleep(100);
        assert !result;
    }

    @Test
    public void LlegirBooleanInvalidOK() throws InterruptedException {
        String input = "n\ns\nsI\n";
        setInputStream(input);
        boolean result = llegirBoolean("Selecciona una opció: ", "si", "no");
        TimeUnit.MILLISECONDS.sleep(100);
        assert result;
    }


}
