# Entorns de desenvolupament: Projecte Final
Desenvolupament d'un sistema de gestió d'inventari per a una botiga.

### **Estructura del projecte Java: descripció tècnica i funcional**
Aquest projecte està organitzat en paquets que representen les diferents funcionalitats del sistema. Cada paquet conté les classes responsables de gestionar una part concreta de lògica, amb una arquitectura que segueix principis de responsabilitat única, modularitat i separació de preocupacions. Això ens permet dissenyar un sistema escalable, col·laboratiu i fàcil de mantenir.

### Paquet producte

Aquest paquet representa el nucli de l'inventari de la botiga. Conté les classes que defineixen i gestionen els productes disponibles per a la compra.

La classe **Producte** encapsula les dades bàsiques d'un producte: el nom, el preu i el stock disponible. Implementa un conjunt de getters i setters que permeten accedir i modificar la seva informació de forma controlada, així com un mètode toString() per mostrar els detalls del producte d’una manera clara per a l’usuari. A nivell tècnic, aquesta classe representa un POJO (Plain Old Java Object) senzill i purament descriptiu.

La classe **GestorProductes** és responsable de totes les operacions relacionades amb la gestió del catàleg: afegir nous productes, cercar-ne per nom (amb opcionalitat), eliminar-los, aplicar descomptes, etc. Aquesta classe actua com a capa de servei interna que encapsula les regles de negoci relacionades amb els productes. El seu ús permet centralitzar la lògica d'accés i manipulació dels objectes Producte i garantir consistència a tota l’aplicació.

### Paquet usuari

Aquest paquet es dedica a gestionar la identitat i el rol dels usuaris que interactuen amb la botiga (clients i administradors, per exemple). Pot esdevenir fonamental per a futurs desenvolupaments amb autenticació, permisos i funcionalitats diferenciades segons el perfil.

La classe **Usuari** modela cada persona que utilitza el sistema. Inclou camps com el nom, el correu electrònic i el rol (administrador o client), entre d’altres possibles dades. Això ens permetria, per exemple, restringir l’accés a determinades funcionalitats (com afegir productes) només a administradors.

La classe **GestorUsuaris** s’encarrega de gestionar la llista d’usuaris registrats i operar-hi: afegir nous usuaris, validar-ne l’existència, obtenir un usuari concret... Aquesta capa, igual que el GestorProductes, separa la lògica d’interacció amb els usuaris del codi de la interfície o del flux principal del programa.

### Paquet venda

Aquest paquet recull la lògica relacionada amb la realització de vendes i el registre d’operacions comercials dins del sistema. És un component clau perquè és el que connecta productes i usuaris dins del flux natural de compra.

La classe **Venda** encapsula una transacció. Conté la data, l’usuari que compra (opcional), una col·lecció de línies de venda (productes i quantitat), i el total. Aquesta classe pot evolucionar fàcilment en un futur per incloure impostos, formes de pagament, o fins i tot factures.

La classe **GestorVendes** centralitza la gestió de l’historial de vendes, i també permet fer cerques o generar informes bàsics. Podria ampliar-se amb funcionalitats com resum de vendes per període o vendes per producte. Aquesta classe assegura la persistència temporal de les vendes realitzades dins de la sessió i manté la coherència del sistema.

### Paquet utilitats

Aquest paquet conté classes de suport reutilitzables, que ajuden a gestionar funcionalitats comunes i repetitives, especialment la interacció amb l’usuari.

La classe **InputHelper** es dissenya per facilitar la lectura de dades de l'usuari des de la consola. Aquest tipus de classe és molt útil quan tens diverses parts del codi on necessites llegir dades del teclat. En lloc de repetir el mateix codi cada vegada que has de fer una lectura, la classe InputHelper centralitza la lògica de validació de les entrades de l'usuari i la fa reutilitzable.  Aquesta classe també ajuda a evitar errors d'entrada com quan l'usuari escriu caràcters en lloc de nombres, o quan introdueix valors que no compleixen amb el format esperat (per exemple, un número negatiu quan s'espera un valor positiu).

```java
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
```

**InputHelper**, per exemple, és una classe de mètodes estàtics que ens permet llegir dades des del teclat de manera segura, validant l’entrada segons el tipus esperat (nombres, decimals, etc.). Aquesta utilitat evita repetir el mateix codi de validació en múltiples llocs i millora la robustesa general del sistema davant d’errors de format.

Aquesta manera d'organitzar el codi és habitual als projectes professionals.

Ens permet:
- Treballar en equip, perquè cada part està separada i ben definida.
- Mantenir el codi net i ordenat.
- Fer proves i millores amb més facilitat.
- Entendre millor com funciona una aplicació de programari real.
