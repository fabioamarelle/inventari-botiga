package main;

import main.java.botiga.Botiga;
import main.java.botiga.producte.Producte;
import main.java.botiga.usuari.Rol;
import main.java.botiga.usuari.Usuari;
import main.java.botiga.venda.Transaccio;
import main.java.botiga.venda.Venda;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Interficie extends JFrame {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private JTextArea textArea;
    private CardLayout cardLayout;
    private JPanel contentPanel;

    public Interficie() {
        setTitle("Gestor Botiga Online");
        setSize(1100, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Crear menú principal
        JPanel mainMenuPanel = createMainMenuPanel();

        // Panel de contenido con CardLayout
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.add(mainMenuPanel, "menu");
        contentPanel.add(createUsuarisPanel(), "usuaris");
        contentPanel.add(createProductesPanel(), "productes");
        contentPanel.add(createVendesPanel(), "vendes");

        // Área de texto con scroll
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Añadir componentes al frame
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);
        add(mainPanel);
    }

    private JPanel createMainMenuPanel() {

        JPanel panel = new JPanel(new GridLayout(3, 1, 20, 20));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        JButton btnUsuaris = new JButton("Gestió Usuaris");
        JButton btnProductes = new JButton("Gestió Productes");
        JButton btnVendes = new JButton("Gestió Vendes");

        btnUsuaris.setFont(new Font("Arial", Font.BOLD, 28));
        btnProductes.setFont(new Font("Arial", Font.BOLD, 28));
        btnVendes.setFont(new Font("Arial", Font.BOLD, 28));

        btnUsuaris.addActionListener(e -> cardLayout.show(contentPanel, "usuaris"));
        btnProductes.addActionListener(e -> cardLayout.show(contentPanel, "productes"));
        btnVendes.addActionListener(e -> cardLayout.show(contentPanel, "vendes"));

        panel.add(btnUsuaris);
        panel.add(btnProductes);
        panel.add(btnVendes);

        return panel;
    }

    private JPanel createUsuarisPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton btnAfegir = new JButton("Afegir Usuari");
        JButton btnEsborrar = new JButton("Esborrar Usuari");
        JButton btnBuscar = new JButton("Buscar Usuari");
        JButton btnLlistar = new JButton("Llistar Usuaris");
        JButton btnTornar = new JButton("Tornar al Menú");

        btnAfegir.addActionListener(e -> afegirUsuari());
        btnEsborrar.addActionListener(e -> esborrarUsuari());
        btnBuscar.addActionListener(e -> buscarUsuari());
        btnLlistar.addActionListener(e -> llistarUsuaris());
        btnTornar.addActionListener(e -> cardLayout.show(contentPanel, "menu"));

        buttonPanel.add(btnAfegir);
        buttonPanel.add(btnEsborrar);
        buttonPanel.add(btnBuscar);
        buttonPanel.add(btnLlistar);
        buttonPanel.add(btnTornar);

        panel.add(buttonPanel, BorderLayout.NORTH);
        return panel;
    }

    private void afegirUsuari() {
        String nom = JOptionPane.showInputDialog("Introdueix el nom:");
        if (nom == null || nom.trim().isEmpty()) return;

        String correu = JOptionPane.showInputDialog("Introdueix el correu electrònic:");
        if (correu == null || correu.trim().isEmpty()) return;
        if (!correu.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            textArea.setText("Error: Format de correu electrònic invàlid");
            return;
        }
        Object[] options = {"Administrador", "Client"};
        int rol = JOptionPane.showOptionDialog(null, "Selecciona el rol:",
                "Rol", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[1]);

        Usuari nouUsuari = new Usuari(nom, correu, rol == 0 ? Rol.ADMINISTRADOR : Rol.CLIENT);
        boolean afegit = Botiga.gestorUsuaris.afegirUsuari(nouUsuari);

        if (afegit) {
            textArea.setText("Usuari afegit correctament:\n" + formatUsuari(nouUsuari));
        } else {
            textArea.setText("Error: Ja existeix un usuari amb aquest correu electrònic");
        }
    }

    private void esborrarUsuari() {
        String correu = JOptionPane.showInputDialog("Introdueix el correu electrònic de l'usuari a esborrar:");
        if (correu == null || correu.trim().isEmpty()) return;

        boolean esborrat = Botiga.gestorUsuaris.esborrarUsuari(correu);
        textArea.setText(esborrat ? "Usuari esborrat correctament" : "Error: Usuari no trobat");
    }

    private void buscarUsuari() {
        String correu = JOptionPane.showInputDialog("Introdueix el correu electrònic de l'usuari a buscar:");
        if (correu == null || correu.trim().isEmpty()) return;

        Usuari usuari = Botiga.gestorUsuaris.obtenirUsuari(correu);
        if (usuari != null) {
            textArea.setText("Usuari trobat:\n" + formatUsuari(usuari));
        } else {
            textArea.setText("Error: Usuari no trobat");
        }
    }

    private void llistarUsuaris() {
        List<Usuari> usuaris = Botiga.gestorUsuaris.getLlistaUsuaris();
        if (usuaris.isEmpty()) {
            textArea.setText("No hi ha usuaris registrats");
            return;
        }

        StringBuilder sb = new StringBuilder("Llistat d'usuaris:\n\n");
        for (Usuari u : usuaris) {
            sb.append(formatUsuari(u)).append("\n");
        }
        textArea.setText(sb.toString());
    }

    private String formatUsuari(Usuari u) {
        return String.format("Nom: %s\nCorreu: %s\nRol: %s\n",
                u.getNom(), u.getCorreuElectronic(), u.getRol());
    }

    private JPanel createProductesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton btnAfegir = new JButton("Afegir Producte");
        JButton btnEsborrar = new JButton("Esborrar Producte");
        JButton btnBuscar = new JButton("Buscar Producte");
        JButton btnLlistar = new JButton("Llistar Productes");
        JButton btnTornar = new JButton("Tornar al Menú");

        btnAfegir.addActionListener(e -> afegirProducte());
        btnEsborrar.addActionListener(e -> esborrarProducte());
        btnBuscar.addActionListener(e -> buscarProducte());
        btnLlistar.addActionListener(e -> llistarProductes());
        btnTornar.addActionListener(e -> cardLayout.show(contentPanel, "menu"));

        buttonPanel.add(btnAfegir);
        buttonPanel.add(btnEsborrar);
        buttonPanel.add(btnBuscar);
        buttonPanel.add(btnLlistar);
        buttonPanel.add(btnTornar);

        panel.add(buttonPanel, BorderLayout.NORTH);
        return panel;
    }

    private void afegirProducte() {
        String nom = JOptionPane.showInputDialog("Introdueix el nom del producte:");
        if (nom == null || nom.trim().isEmpty()) return;

        String preuStr = JOptionPane.showInputDialog("Introdueix el preu del producte:");
        if (preuStr == null || preuStr.trim().isEmpty()) return;

        String stockStr = JOptionPane.showInputDialog("Introdueix el stock del producte:");
        if (stockStr == null || stockStr.trim().isEmpty()) return;

        try {
            double preu = Double.parseDouble(preuStr);
            int stock = Integer.parseInt(stockStr);

            Producte nouProducte = new Producte(nom, preu, stock);
            Botiga.gestorProductes.afegirProducte(nouProducte);
            textArea.setText("Producte afegit correctament:\n" + formatProducte(nouProducte));
        } catch (NumberFormatException e) {
            textArea.setText("Error: Format numèric incorrecte");
        }
    }

    private void esborrarProducte() {
        String nom = JOptionPane.showInputDialog("Introdueix el nom del producte a esborrar:");
        if (nom == null || nom.trim().isEmpty()) return;

        boolean esborrat = Botiga.gestorProductes.eliminarProducte(nom);
        textArea.setText(esborrat ? "Producte esborrat correctament" : "Error: Producte no trobat");
    }

    private void buscarProducte() {
        String nom = JOptionPane.showInputDialog("Introdueix el nom del producte a buscar:");
        if (nom == null || nom.trim().isEmpty()) return;

        ArrayList<Producte> productes = Botiga.gestorProductes.cercarProductesPerNom(nom);
        if (productes.isEmpty()) {
            textArea.setText("No s'han trobat productes");
            return;
        }

        StringBuilder sb = new StringBuilder("Productes trobats:\n\n");
        for (Producte p : productes) {
            sb.append(formatProducte(p)).append("\n");
        }
        textArea.setText(sb.toString());
    }

    private void llistarProductes() {
        List<Producte> productes = Botiga.gestorProductes.getCataleg();
        if (productes.isEmpty()) {
            textArea.setText("No hi ha productes registrats");
            return;
        }

        StringBuilder sb = new StringBuilder("Llistat de productes:\n\n");
        for (Producte p : productes) {
            sb.append(formatProducte(p)).append("\n");
        }
        textArea.setText(sb.toString());
    }

    private String formatProducte(Producte p) {
        return String.format("Nom: %s\nPreu: %.2f€\nStock: %d\n",
                p.getNom(), p.getPreu(), p.getStock());
    }

    private JPanel createVendesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton btnAfegir = new JButton("Afegir Venda");
        JButton btnEsborrar = new JButton("Esborrar Venda");
        JButton btnBuscar = new JButton("Buscar Venda");
        JButton btnLlistar = new JButton("Llistar Vendes");
        JButton btnTornar = new JButton("Tornar al Menú");

        btnAfegir.addActionListener(e -> afegirVenda());
        btnEsborrar.addActionListener(e -> esborrarVenda());
        btnBuscar.addActionListener(e -> buscarVenda());
        btnLlistar.addActionListener(e -> llistarVendes());
        btnTornar.addActionListener(e -> cardLayout.show(contentPanel, "menu"));

        buttonPanel.add(btnAfegir);
        buttonPanel.add(btnEsborrar);
        buttonPanel.add(btnBuscar);
        buttonPanel.add(btnLlistar);
        buttonPanel.add(btnTornar);

        panel.add(buttonPanel, BorderLayout.NORTH);
        return panel;
    }

    private void afegirVenda() {
        // Implementación similar a la de consola pero con JOptionPane
        // Adaptada para usar los métodos existentes de GestorVendes
        try {
            String dataStr = JOptionPane.showInputDialog("Introdueix la data de la venda (DD-MM-YYYY):");
            if (dataStr == null || dataStr.trim().isEmpty()) return;

            LocalDate data = LocalDate.parse(dataStr, DATE_FORMATTER);

            String correu = JOptionPane.showInputDialog("Introdueix el correu electrònic de l'usuari:");
            if (correu == null || correu.trim().isEmpty()) return;

            Usuari usuari = Botiga.gestorUsuaris.obtenirUsuari(correu);
            if (usuari == null) {
                textArea.setText("Error: Usuari no trobat");
                return;
            }

            Venda novaVenda = new Venda(data, usuari);
            boolean continuar = true;

            while (continuar) {
                String nomProducte = JOptionPane.showInputDialog("Introdueix el nom del producte:");
                if (nomProducte == null || nomProducte.trim().isEmpty()) break;

                Producte producte = Botiga.gestorProductes.cercarProducte(nomProducte);
                if (producte == null) {
                    textArea.setText("Error: Producte no trobat");
                    continue;
                }

                String quantitatStr = JOptionPane.showInputDialog("Introdueix la quantitat:");
                if (quantitatStr == null || quantitatStr.trim().isEmpty()) break;

                try {
                    int quantitat = Integer.parseInt(quantitatStr);
                    if (producte.getStock() < quantitat) {
                        textArea.setText("Error: Stock insuficient");
                        continue;
                    }

                    Transaccio transaccio = new Transaccio(producte, quantitat);
                    novaVenda.afegirTransaccio(transaccio);
                    producte.setStock(producte.getStock() - quantitat);

                    int opcio = JOptionPane.showConfirmDialog(null,
                            "Vols afegir un altre producte?",
                            "Continuar",
                            JOptionPane.YES_NO_OPTION);
                    continuar = opcio == JOptionPane.YES_OPTION;
                } catch (NumberFormatException e) {
                    textArea.setText("Error: Quantitat no vàlida");
                }
            }

            if (!novaVenda.getLlistaTransaccio().isEmpty()) {
                Botiga.gestorVendes.afegirVenda(novaVenda);
                textArea.setText("Venda afegida correctament:\n" + formatVenda(novaVenda));
            } else {
                textArea.setText("Venda cancel·lada: No s'han afegit productes");
            }
        } catch (DateTimeParseException e) {
            textArea.setText("Error: Format de data incorrecte (DD-MM-YYYY)");
        }
    }

    private void esborrarVenda() {
        try {
            String dataStr = JOptionPane.showInputDialog("Introdueix la data de la venda (DD-MM-YYYY):");
            if (dataStr == null || dataStr.trim().isEmpty()) return;

            LocalDate data = LocalDate.parse(dataStr, DATE_FORMATTER);

            String correu = JOptionPane.showInputDialog("Introdueix el correu electrònic de l'usuari:");
            if (correu == null || correu.trim().isEmpty()) return;

            ArrayList<Venda> vendes = Botiga.gestorVendes.buscarVenda(correu, data);
            if (vendes.isEmpty()) {
                textArea.setText("No s'han trobat vendes");
                return;
            }

            for (Venda v : vendes) {
                Botiga.gestorVendes.esborrarVenda(v);
            }

            textArea.setText("Venda/es esborrada/es correctament");
        } catch (DateTimeParseException e) {
            textArea.setText("Error: Format de data incorrecte (DD-MM-YYYY)");
        }
    }

    private void buscarVenda() {
        try {
            String dataStr = JOptionPane.showInputDialog("Introdueix la data de la venda (DD-MM-YYYY):");
            if (dataStr == null || dataStr.trim().isEmpty()) return;

            LocalDate data = LocalDate.parse(dataStr, DATE_FORMATTER);

            String correu = JOptionPane.showInputDialog("Introdueix el correu electrònic de l'usuari:");
            if (correu == null || correu.trim().isEmpty()) return;

            ArrayList<Venda> vendes = Botiga.gestorVendes.buscarVenda(correu, data);
            if (vendes.isEmpty()) {
                textArea.setText("No s'han trobat vendes");
                return;
            }

            StringBuilder sb = new StringBuilder("Vendes trobades:\n\n");
            for (Venda v : vendes) {
                sb.append(formatVenda(v)).append("\n");
            }
            textArea.setText(sb.toString());
        } catch (DateTimeParseException e) {
            textArea.setText("Error: Format de data incorrecte (DD-MM-YYYY)");
        }
    }

    private void llistarVendes() {
        List<Venda> vendes = Botiga.gestorVendes.getLlistaVendes();
        if (vendes.isEmpty()) {
            textArea.setText("No hi ha vendes registrades");
            return;
        }

        StringBuilder sb = new StringBuilder("Llistat de vendes:\n\n");
        for (Venda v : vendes) {
            sb.append(formatVenda(v)).append("\n");
        }
        textArea.setText(sb.toString());
    }

    private String formatVenda(Venda v) {
        StringBuilder sb = new StringBuilder();
        sb.append("Data: ").append(v.getData().format(DATE_FORMATTER)).append("\n");
        sb.append("Usuari: ").append(v.getUsuari().getNom()).append("\n");
        sb.append("Productes:\n");

        double total = 0;
        for (Transaccio t : v.getLlistaTransaccio()) {
            Producte p = t.getProducte();
            double subtotal = p.getPreu() * t.getQuantitat();
            sb.append(String.format(" - %s (%.2f€) x%d = %.2f€\n",
                    p.getNom(), p.getPreu(), t.getQuantitat(), subtotal));
            total += subtotal;
        }

        sb.append(String.format("TOTAL: %.2f€\n", total));
        return sb.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Interficie interfaz = new Interficie();
            interfaz.setVisible(true);
        });
    }
}