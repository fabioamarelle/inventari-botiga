package main.java.botiga.utilitats;

import java.util.Scanner;

public class InputHelper {
    private static Scanner scanner = new Scanner(System.in);
    public static int llegirEnter() {
        Scanner scanner = new Scanner(System.in);
        int num = 0;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.print("Introdueix un número enter: ");
                num = Integer.parseInt(scanner.nextLine());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: No és un número vàlid. Torna a intentar-ho.");
            }
        }
        return num;
    }
    public static int llegirEnterPositiu() {
        int num;
        do {
            num = llegirEnter();
            if (num <= 0) {
                System.out.println("Introdueix un número positiu.");
            }
        } while (num <= 0);
        return num;
    }
    public static int llegirDecimal() {
        double num = 0.0;
        boolean valid = false;
        while (!valid) {
            try{
                System.out.println("Introdueix un numero decimal.");
                num = Double.parseDouble(scanner.nextLine());
                valid = true;
            }catch (NumberFormatException e){
                System.out.println("Introdueix un numero decimal, el que has posat no es valid.");
            }
        }
        return (int) num;
    }
    public static String Stringbuit(){
        String input = "";
        do
        {
            System.out.println("Introdueix un text");
            input = scanner.nextLine();
            if (input.isEmpty()){
                System.out.println("El text estava buit, introdueix un de valid ");
        }
        }
        while (input.isEmpty());
        return input;
    }
}
