package org.example;

import java.io.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Main {
    private static final String FILE_PATH = "database.dat";
    public static void main(String[] args) {

        Application app = loadData();
        if(app==null) {
            return;
        }
        int option;
        do {
            option = app.showMenu();
            switch (option) {
                case 1:
                    try {
                        app.addAmmunition();
                    } catch (AmmunitionException au) {
                        System.out.println(au.getMessage());
                    }
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
                    try {
                        app.filter();
                    } catch (AmmunitionException au) {
                        System.out.println(au.getMessage());
                    }
                    break;
            }
        } while (option != 0);
        saveData(app);
    }

    private static Application loadData() {
        System.out.println("Загрузка списка амуниции из файла:");
        try {
            FileInputStream fis = new FileInputStream(FILE_PATH);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Application app = (Application) ois.readObject();
            System.out.println("\nИнформация загружена!\n");
            return app;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка загрузки");
        }
        return null;
    }

    private static void saveData(Application app) {
        try {
            FileOutputStream fos= new FileOutputStream(FILE_PATH);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(app);
            oos.close();
            System.out.println("\nИнформация сохранена!\n");
        } catch (IOException e) {
            System.out.println("При сохранении возникла ошибка");
        }
    }
}
