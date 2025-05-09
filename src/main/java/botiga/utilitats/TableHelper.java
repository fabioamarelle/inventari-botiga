package main.java.botiga.utilitats;

import java.util.*;

public class TableHelper {

    public static <K, V> String hashMapATaula(String nomClau, String nomValor, HashMap<K, V> hashMap) {
        int longitudMaximaClau = nomClau.length();
        int longitudMaximaValor = nomValor.length();

        // Calcula l'amplada màxima de cada columna
        for (Map.Entry<K, V> entrada : hashMap.entrySet()) {
            int longitudClau = String.valueOf(entrada.getKey()).length();
            int longitudValor = String.valueOf(entrada.getValue()).length();
            if (longitudClau > longitudMaximaClau) longitudMaximaClau = longitudClau;
            if (longitudValor > longitudMaximaValor) longitudMaximaValor = longitudValor;
        }

        StringBuilder taula = new StringBuilder();

        // Vora superior
        taula.append("+").append("-".repeat(longitudMaximaClau + 2)).append("+")
                .append("-".repeat(longitudMaximaValor + 2)).append("+\n");

        // Capçalera
        taula.append(String.format("| %-"+longitudMaximaClau+"s | %-"+longitudMaximaValor+"s |%n", nomClau, nomValor));
        taula.append("+").append("-".repeat(longitudMaximaClau + 2)).append("+")
                .append("-".repeat(longitudMaximaValor + 2)).append("+\n");

        // Contingut de les files
        for (Map.Entry<K, V> entrada : hashMap.entrySet()) {
            taula.append(String.format("| %-"+longitudMaximaClau+"s | %-"+longitudMaximaValor+"s |%n",
                    entrada.getKey(), entrada.getValue()));
        }

        // Vora inferior
        taula.append("+").append("-".repeat(longitudMaximaClau + 2)).append("+")
                .append("-".repeat(longitudMaximaValor + 2)).append("+\n");

        return taula.toString();
    }

    public static <T> String llistaATaula(String capcalera, List<T> llista) {
        int longitudMaxima = capcalera.length();

        // Calcula l'amplada màxima
        for (T element : llista) {
            int longitud = String.valueOf(element).length();
            if (longitud > longitudMaxima) longitudMaxima = longitud;
        }

        StringBuilder taula = new StringBuilder();

        // Vora superior
        taula.append("+").append("-".repeat(longitudMaxima + 2)).append("+\n");

        // Capçalera
        taula.append(String.format("| %-"+longitudMaxima+"s |%n", capcalera));
        taula.append("+").append("-".repeat(longitudMaxima + 2)).append("+\n");

        // Files
        for (T element : llista) {
            taula.append(String.format("| %-"+longitudMaxima+"s |%n", element));
        }

        // Vora inferior
        taula.append("+").append("-".repeat(longitudMaxima + 2)).append("+\n");

        return taula.toString();
    }
}
