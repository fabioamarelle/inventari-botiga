package main.java.botiga.utilitats;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class InputHelper {
    private static final Scanner scanner = new Scanner(System.in);

    public static int llegirEnter(String missatge) {
        Scanner scanner = new Scanner(System.in);
        int num = 0;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.print(missatge);
                num = Integer.parseInt(scanner.nextLine());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: No és un número vàlid. Torna a intentar-ho.");
            }
        }
        return num;
    }
    public static int llegirEnterPositiu(String missatge) {
        Scanner scanner = new Scanner(System.in);
        int num = 0;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.print(missatge);
                num = Integer.parseInt(scanner.nextLine());
                if (num > 0) {
                    valid = true;
                } else {
                    System.out.println("Error: El número no pot ser negatiu. Torna a intentar-ho.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: No és un número vàlid. Torna a intentar-ho.");
            }
        }
        return num;
    }
    public static double llegirDecimal(String missatge) {
        Scanner scanner = new Scanner(System.in);
        double num = 0.0;
        boolean valid = false;
        while (!valid) {
            try{
                System.out.print(missatge);
                num = Double.parseDouble(scanner.nextLine());
                valid = true;
            } catch (NumberFormatException e){
                System.out.println("Introdueix un número decimal, el que has posat no és vàlid.");
            }
        }
        return num;
    }
    public static String llegirString(String missatge){
        Scanner scanner = new Scanner(System.in);
        String input;
        do
        {
            System.out.print(missatge);
            input = scanner.nextLine();
            if (input.isEmpty()){
                System.out.println("El text estava buit, introdueix un de vàlid.");
            }
        }
        while (input.isEmpty());
        return input;
    }

    public static boolean llegirBoolean(String missatge, String opcioTrue, String opcioFalse) {
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            System.out.print(missatge + "(" + opcioTrue + " / " + opcioFalse + "): ");
            input = scanner.nextLine().trim().toLowerCase();

            if (input.equals(opcioTrue.trim().toLowerCase())) {
                return true;
            } else if (input.equals(opcioFalse.trim().toLowerCase())) {
                return false;
            } else {
                System.out.println("Error: Resposta no reconeguda. (" + opcioTrue + " / " + opcioFalse + ")");
            }

        }


    }
    public static LocalDate llegirData(String missatge){
        LocalDate data;

        while (true) {
            try {
                String dataString = InputHelper.llegirString(missatge + "(DD-MM-YYYY): ");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                data = LocalDate.parse(dataString, formatter);
                break;
            } catch (Exception e) {
                System.out.println("Error: Format de la data incorrecte.");
            }
        }
        return data;
    }

    public static void enterPerContinuar(){
        System.out.print("Prèm Enter per a continuar...");
        scanner.nextLine();
    }
}
