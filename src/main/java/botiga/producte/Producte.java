package main.java.botiga.producte;

public class Producte {

        private String nom;
        private double preu;
        private int stock;


        public Producte(String nom, double preu, int stock) {
            this.nom = nom;
            this.preu = preu;
            this.stock = stock;
        }


        public String getNom() {
            return nom;
        }

        public double getPreu() {
            return preu;
        }

        public int getStock() {
            return stock;
        }


        public void setNom(String nom) {
            this.nom = nom;
        }

        public void setPreu(double preu) {
            this.preu = preu;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }


        @Override
        public String toString() {
            return "Producte{" + "nom='" + nom + '\'' + ", preu=" + preu + ", stock=" + stock + '}';
        }
    }


