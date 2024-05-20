package org.example;
public class Main {
    public static void main(String[] args) {
        Application app = new Application();
        int option;
        do {
            option = app.showMenu();
            switch (option) {
                case 1:
                    app.addAmmunition();
                    break;
                case 2:
                    app.printAmmunitionList();
                    break;
                case 3:
                    app.addAmmunitionToBiker();
                    break;
                case 4:
                    app.calcPrice();
                    break;
                case 5:
                    app.sort();
                    break;
                case 6:
                    app.filter();
                    break;
            }
        } while (option != 0);
    }
}