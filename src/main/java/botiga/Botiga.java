package main.java.botiga;

import main.java.botiga.producte.GestorProductes;
import main.java.botiga.producte.Producte;
import main.java.botiga.usuari.GestorUsuaris;
import main.java.botiga.usuari.Usuari;
import main.java.botiga.utilitats.InputHelper;
import main.java.botiga.utilitats.TableHelper;
import main.java.botiga.venda.GestorVendes;
import main.java.botiga.venda.Venda;

import java.time.LocalDate;
import java.util.ArrayList;

public class Botiga {
    public static final GestorUsuaris gestorUsuaris = new GestorUsuaris();
    public static final GestorVendes gestorVendes = new GestorVendes();
    public static final GestorProductes gestorProductes = new GestorProductes();
    private static boolean sortirMenuUsuaris = false;
    private static boolean sortirMenuVendes = false;
    private static boolean sortirMenuProductes = false;

    public static void menu(){
        boolean sortirMenu = false;
        while (!sortirMenu){
            System.out.println("Gestor d'una botiga online");
            System.out.println("\t [1] Gestionar usuaris");
            System.out.println("\t [2] Gestionar productes");
            System.out.println("\t [3] Gestionar vendes");
            System.out.println("\t [4] Sortir");
            int menuPrincipal = InputHelper.llegirEnter("Selecciona una opció: ");
            switch (menuPrincipal) {
                case 1:
                    Botiga.menuGestorUsuaris();
                    sortirMenuUsuaris = false;
                    break;
                case 2:
                    Botiga.menuGestorProductes();
                    sortirMenuProductes = false;
                    break;
                case 3:
                    Botiga.menuGestorVendes();
                    sortirMenuVendes = false;
                    break;
                case 4:
                    sortirMenu = true;
                    break;
            }
        }
    }

    public static void menuGestorUsuaris(){
        while (!sortirMenuUsuaris){
            System.out.println("Gestor d'usuaris");
            System.out.println("\t [1] Afegir usuaris");
            System.out.println("\t [2] Esborrar usuaris");
            System.out.println("\t [3] Buscar usuaris");
            System.out.println("\t [4] Llistar usuaris");
            System.out.println("\t [5] Sortir");
            int menuUsuaris = InputHelper.llegirEnter("Selecciona una opció: ");
            switch (menuUsuaris){
                case 1:
                    gestorUsuaris.afegirUsuari();
                    InputHelper.enterPerContinuar();
                    break;
                case 2:
                    gestorUsuaris.esborrarUsuari();
                    InputHelper.enterPerContinuar();
                    break;
                case 3:
                    Usuari usuariTrobat = gestorUsuaris.obtenirUsuari();
                    if (usuariTrobat != null){
                        System.out.println("Usuari trobat: ");
                        System.out.println(usuariTrobat);
                    } else {
                        System.out.println("Error: Usuari no trobat.");
                    }
                    InputHelper.enterPerContinuar();
                    break;
                case 4:
                    System.out.println("Mostrant tots els usuaris: ");
                    ArrayList<Usuari> llistaUsuaris = gestorUsuaris.getTotsUsuaris();
                    System.out.println(TableHelper.llistaATaula("Usuaris", llistaUsuaris));
                    InputHelper.enterPerContinuar();
                    break;
                case 5:
                    sortirMenuUsuaris = true;
                    break;
                default:
                    System.out.println("Error: No és una opció vàlida. Torna a intentar-ho.");
                    InputHelper.enterPerContinuar();
            }
        }
    }

    public static void menuGestorProductes() {
        while (!sortirMenuProductes) {
            System.out.println("Gestor de productes");
            System.out.println("\t [1] Afegir productes");
            System.out.println("\t [2] Esborrar productes");
            System.out.println("\t [3] Buscar productes");
            System.out.println("\t [4] Llistar productes");
            System.out.println("\t [5] Sortir");
            int menuProductes = InputHelper.llegirEnter("Selecciona una opció: ");
            switch (menuProductes) {
                case 1:
                    gestorProductes.afegirProducte();
                    InputHelper.enterPerContinuar();
                    break;
                case 2:
                    boolean producteEsborrat = gestorProductes.eliminarProducte();
                    if (producteEsborrat) {
                        System.out.println("S'ha esborrat el producte.");
                    } else {
                        System.out.println("Error. No s'ha trobat el producte.");
                    }
                    break;
                case 3:
                    ArrayList<Producte> productesTrobats = gestorProductes.cercarProductesPerNom();
                    if (!productesTrobats.isEmpty()) {
                        if (productesTrobats.size() == 1){
                            System.out.println("S'ha trobat el següent producte: ");
                        } else {
                            System.out.println("S'han trobat els següents producte: ");
                        }
                        System.out.println(TableHelper.llistaATaula("Productes", productesTrobats));
                    } else {
                        System.out.println("Error: Producte no trobat.");
                    }
                    break;
                case 4:
                    System.out.println(TableHelper.llistaATaula("Productes", gestorProductes.getCataleg()));
                case 5:
                    sortirMenuProductes = true;
                    break;
                default:
                    System.out.println("Error: No és una opció vàlida. Torna a intentar-ho.");
                    InputHelper.enterPerContinuar();
            }
        }
    }

    public static void menuGestorVendes(){
        while (!sortirMenuVendes){
            System.out.println("Gestor de vendes");
            System.out.println("\t [1] Afegir vendes");
            System.out.println("\t [2] Esborrar vendes");
            System.out.println("\t [3] Buscar vendes");
            System.out.println("\t [4] Llistar vendes");
            System.out.println("\t [5] Sortir");
            int menuVendes = InputHelper.llegirEnter("Selecciona una opció: ");
            switch (menuVendes) {
                case 1:
                    gestorVendes.afegirVenda(gestorProductes);
                    InputHelper.enterPerContinuar();
                    break;
                case 2:
                    gestorVendes.esborrarVenda();
                    InputHelper.enterPerContinuar();
                    break;
                case 3:
                    System.out.println("\t [1] Buscar una venda directament");
                    System.out.println("\t [2] Buscar per període de temps");
                    System.out.println("\t [3] Buscar per producte");
                    int menuBuscarVendes = InputHelper.llegirEnter("Selecciona una opció: ");
                    switch (menuBuscarVendes) {
                        case 1:
                            ArrayList<Venda> vendesTrobades = gestorVendes.buscarVenda();
                            if (!vendesTrobades.isEmpty()) {
                                if (vendesTrobades.size() == 1) {
                                    System.out.println("S'ha trobat la següent venda: ");
                                } else {
                                    System.out.println("S'han trobat les següents vendes: ");
                                }
                                System.out.println(TableHelper.llistaATaula("Vendes", vendesTrobades));
                            } else {
                                System.out.println("Error: Venda no trobada.");
                            }
                            break;

                        case 2:
                            LocalDate dataInici = InputHelper.llegirData("Introdueix la data d'inici: ");
                            LocalDate dataFi = InputHelper.llegirData("Introdueix la data de fi: ");

                            ArrayList<Venda> vendesPerPeriode = gestorVendes.vendesPeriode(dataInici, dataFi);
                            if (!vendesPerPeriode.isEmpty()) {
                                if (vendesPerPeriode.size() == 1) {
                                    System.out.println("S'ha trobat la següent venda: ");
                                } else {
                                    System.out.println("S'han trobat les següents vendes: ");
                                }
                                System.out.println(TableHelper.llistaATaula("Vendes", vendesPerPeriode));
                            } else {
                                System.out.println("Error: No és una opció vàlida. Torna a intentar-ho.");
                            }
                            InputHelper.enterPerContinuar();
                            break;

                        case 3:
                            ArrayList<Producte> productesTrobats = gestorProductes.cercarProductesPerNom();
                            if (productesTrobats.isEmpty()) {
                                System.out.println("No s'han trobat productes.");
                            } else if (productesTrobats.size() >= 2) {
                                System.out.println("S'ha trobat més d'un producte. No es pot realitzar la cerca.");
                            } else {
                                ArrayList<Venda> vendesPerProducte = gestorVendes.vendesProducte(productesTrobats.get(0));
                                System.out.println(TableHelper.llistaATaula("Vendes", vendesPerProducte));
                            }
                            InputHelper.enterPerContinuar();
                            break;
                        default:
                            System.out.println("Error: No és una opció vàlida. Torna a intentar-ho.");
                    }
                    break;


                case 4:
                    System.out.println("Mostrant totes les vendes: ");
                    ArrayList<Venda> llistaVendes = gestorVendes.getLlistaVendes();
                    System.out.println(TableHelper.llistaATaula("Vendes", llistaVendes));
                    InputHelper.enterPerContinuar();
                    break;
                case 5:
                    sortirMenuVendes = true;
                    break;
                default:
                    System.out.println("Error: No és una opció vàlida. Torna a intentar-ho.");
                    InputHelper.enterPerContinuar();
            }
        }
    }
}
