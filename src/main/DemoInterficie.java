package main;

import main.java.botiga.Botiga;
import main.java.botiga.producte.Producte;
import main.java.botiga.usuari.Rol;
import main.java.botiga.usuari.Usuari;
import main.java.botiga.venda.Transaccio;
import main.java.botiga.venda.Venda;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class DemoInterficie extends JFrame {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final Color PRIMARY_COLOR = new Color(0, 102, 204);
    private static final Color SECONDARY_COLOR = new Color(240, 240, 240);
    private static final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 24);
    private static final Font BUTTON_FONT = new Font("Segoe UI", Font.PLAIN, 16);
    private static final Font TEXT_FONT = new Font("Consolas", Font.PLAIN, 14);

    private JTextArea textArea;
    private CardLayout cardLayout;
    private JPanel contentPanel;

    public DemoInterficie() {
        setTitle("Gestor Botiga Online");
        setSize(1100, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(SECONDARY_COLOR);

        JPanel mainMenuPanel = createMainMenuPanel();

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPanel.setBackground(SECONDARY_COLOR);
        contentPanel.add(mainMenuPanel, "menu");
        contentPanel.add(createUsuarisPanel(), "usuaris");
        contentPanel.add(createProductesPanel(), "productes");
        contentPanel.add(createVendesPanel(), "vendes");

        textArea = new JTextArea(5, 20);
        textArea.setEditable(false);
        textArea.setFont(TEXT_FONT);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(Color.WHITE);
        textArea.setForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(),
                "Informació",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                BUTTON_FONT,
                PRIMARY_COLOR));
        scrollPane.setPreferredSize(new Dimension(getWidth(), 200));

        mainPanel.add(createHeaderPanel(), BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        textPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(textPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(PRIMARY_COLOR);
        headerPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));

        JLabel titleLabel = new JLabel("Gestor Botiga Online");
        titleLabel.setFont(HEADER_FONT);
        titleLabel.setForeground(Color.WHITE);

        headerPanel.add(titleLabel);
        headerPanel.add(Box.createHorizontalGlue());

        return headerPanel;
    }

    private JPanel createMainMenuPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(50, 100, 50, 100));
        panel.setBackground(SECONDARY_COLOR);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 0, 15, 0);
        gbc.weightx = 1;
        gbc.weighty = 1;

        JButton btnUsuaris = createMenuButton("Gestió Usuaris", "");
        JButton btnProductes = createMenuButton("Gestió Productes", "");
        JButton btnVendes = createMenuButton("Gestió Vendes", "");

        btnUsuaris.addActionListener(e -> cardLayout.show(contentPanel, "usuaris"));
        btnProductes.addActionListener(e -> cardLayout.show(contentPanel, "productes"));
        btnVendes.addActionListener(e -> cardLayout.show(contentPanel, "vendes"));

        panel.add(btnUsuaris, gbc);
        panel.add(btnProductes, gbc);
        panel.add(btnVendes, gbc);

        return panel;
    }

    private JButton createMenuButton(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 20));
        button.setForeground(new Color(0, 102, 204));
        button.setBackground(PRIMARY_COLOR);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createEmptyBorder(15, 25, 15, 25)));

        return button;
    }

    private JPanel createUsuarisPanel() {
        return createManagementPanel(
                "Gestió d'Usuaris",
                new String[]{"Afegir Usuari", "Esborrar Usuari", "Buscar Usuari", "Llistar Usuaris"},
                new Runnable[]{this::afegirUsuari, this::esborrarUsuari, this::buscarUsuari, this::llistarUsuaris}
        );
    }

    private JPanel createProductesPanel() {
        return createManagementPanel(
                "Gestió de Productes",
                new String[]{"Afegir Producte", "Esborrar Producte", "Buscar Producte", "Llistar Productes"},
                new Runnable[]{this::afegirProducte, this::esborrarProducte, this::buscarProducte, this::llistarProductes}
        );
    }

    private JPanel createVendesPanel() {
        return createManagementPanel(
                "Gestió de Vendes",
                new String[]{"Afegir Venda", "Esborrar Venda", "Buscar Venda", "Llistar Vendes"},
                new Runnable[]{this::afegirVenda, this::esborrarVenda, this::buscarVenda, this::llistarVendes}
        );
    }

    private JPanel createManagementPanel(String title, String[] buttonLabels, Runnable[] actions) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(SECONDARY_COLOR);

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(SECONDARY_COLOR);
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(HEADER_FONT);
        titleLabel.setForeground(PRIMARY_COLOR);
        titlePanel.add(titleLabel);

        JPanel buttonPanel = new JPanel(new GridLayout(1, buttonLabels.length, 10, 0));
        buttonPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        buttonPanel.setBackground(SECONDARY_COLOR);

        for (int i = 0; i < buttonLabels.length; i++) {
            JButton button = new JButton(buttonLabels[i]);
            button.setFont(BUTTON_FONT);
            button.setBackground(Color.WHITE);
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(PRIMARY_COLOR, 1),
                    BorderFactory.createEmptyBorder(8, 15, 8, 15)));
            int finalI = i;
            button.addActionListener(e -> actions[finalI].run());
            buttonPanel.add(button);
        }

        JButton btnTornar = new JButton("Tornar al Menú Principal");
        btnTornar.setFont(BUTTON_FONT);
        btnTornar.setBackground(PRIMARY_COLOR);
        btnTornar.setForeground(new Color(0, 102, 204));
        btnTornar.setFocusPainted(false);
        btnTornar.addActionListener(e -> cardLayout.show(contentPanel, "menu"));
        btnTornar.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(SECONDARY_COLOR);
        bottomPanel.add(btnTornar);

        panel.add(titlePanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

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

    private void afegirVenda() {
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
        // Productes
        Producte producte1 = new Producte("AMD Ryzen 7 9800X3D 4.7/5.2GHz", 749.99, 85);
        Producte producte2 = new Producte("AMD Ryzen 7 7800X3D 4.2 GHz/5 GHz", 699.99, 49);

        Producte producte3 = new Producte("GeForce RTX 4060 V2 OC Edition 8GB GDDR6 DLSS3", 342.99, 36);
        Producte producte4 = new Producte("RTX 5070 VENTUS 2X OC 12GB GDDR7 Reflex 2 DLSS4", 342.99, 94);

        Producte producte5 = new Producte("Corsair Vengeance DDR4 3200 PC4-25600 32GB 2x16GB", 67.99, 48);
        Producte producte6 = new Producte("Crucial SO-DIMM DDR4 3200Mhz PC4-25600 8GB", 14.99, 24);

        Botiga.gestorProductes.afegirProducte(producte1);
        Botiga.gestorProductes.afegirProducte(producte2);
        Botiga.gestorProductes.afegirProducte(producte3);
        Botiga.gestorProductes.afegirProducte(producte4);
        Botiga.gestorProductes.afegirProducte(producte5);
        Botiga.gestorProductes.afegirProducte(producte6);

        // Usuaris
        Usuari usuari1 = new Usuari("Fabio Amarelle Rodrigues", "amarellerodriguesfabio@gmail.com", Rol.ADMINISTRADOR);
        Usuari usuari2 = new Usuari("Aitor Alcalá Jiménez", "alcalajimenezaitor@gmail.com", Rol.ADMINISTRADOR);
        Usuari usuari3 = new Usuari("Joel Oliva Domènech", "domenecholivajoel@gmail.com", Rol.ADMINISTRADOR);

        Usuari usuari4 = new Usuari("Alex Cuenca Gallardo", "cuencagallardoalex@gmail.com", Rol.CLIENT);
        Usuari usuari5 = new Usuari("Yassine Aktaou Larej", "aktaoularejyassine@gmail.com", Rol.CLIENT);
        Usuari usuari6 = new Usuari("Edgar Castellanos Contreras", "contrerascastellanosedgar@gmail.com", Rol.CLIENT);
        Usuari usuari7 = new Usuari("Biel Urdi Agrafojo", "agrafojourdibiel@gmail.com", Rol.CLIENT);

        Botiga.gestorUsuaris.afegirUsuari(usuari1);
        Botiga.gestorUsuaris.afegirUsuari(usuari2);
        Botiga.gestorUsuaris.afegirUsuari(usuari3);
        Botiga.gestorUsuaris.afegirUsuari(usuari4);
        Botiga.gestorUsuaris.afegirUsuari(usuari5);
        Botiga.gestorUsuaris.afegirUsuari(usuari6);
        Botiga.gestorUsuaris.afegirUsuari(usuari7);

        // Vendes
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        Venda venda1 = new Venda(LocalDate.parse("13-05-2025", formatter), usuari1);
        venda1.afegirTransaccio(new Transaccio(producte1, 9));
        venda1.afegirTransaccio(new Transaccio(producte6, 2));
        Botiga.gestorVendes.afegirVenda(venda1);

        Venda venda2 = new Venda(LocalDate.parse("09-01-2025", formatter), usuari6);
        venda2.afegirTransaccio(new Transaccio(producte5, 1));
        venda2.afegirTransaccio(new Transaccio(producte2, 2));
        Botiga.gestorVendes.afegirVenda(venda2);

        Venda venda3 = new Venda(LocalDate.parse("01-12-2024", formatter), usuari2);
        venda3.afegirTransaccio(new Transaccio(producte5, 6));
        venda3.afegirTransaccio(new Transaccio(producte3, 2));
        Botiga.gestorVendes.afegirVenda(venda3);

        Venda venda4 = new Venda(LocalDate.parse("22-12-2024", formatter), usuari5);
        venda4.afegirTransaccio(new Transaccio(producte2, 2));
        venda4.afegirTransaccio(new Transaccio(producte4, 1));
        Botiga.gestorVendes.afegirVenda(venda4);

        Venda venda5 = new Venda(LocalDate.parse("29-03-2025", formatter), usuari4);
        venda5.afegirTransaccio(new Transaccio(producte1, 1));
        venda5.afegirTransaccio(new Transaccio(producte6, 4));
        Botiga.gestorVendes.afegirVenda(venda5);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            //
        }

        SwingUtilities.invokeLater(() -> {
            Interficie interfaz = new Interficie();
            interfaz.setVisible(true);
        });
    }
}