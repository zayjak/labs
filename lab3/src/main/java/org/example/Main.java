package org.example;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import static java.lang.System.exit;

public class Main {
    private static final String FILE_PATH = "database.dat";
    public static void main(String[] args) {

        Application app = loadData();
        if(app==null) {
            app = new Application();

            app.getAmmunitions().add(new BikerAmmunition("шлем", 0.6, 95.50));
            app.getAmmunitions().add(new BikerAmmunition("перчатки", 0.15, 155.25));
            app.getAmmunitions().add(new BikerAmmunition("штаны", 0.335, 235.25));
            app.getAmmunitions().add(new BikerAmmunition("куртка", 0.615, 425.25));
            app.getAmmunitions().add(new BikerAmmunition("комбинезон", 0.795, 950.25));
        }
        JFrame loginWindow = new JFrame("Вход");
        loginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создаем диалоговое окно
        JDialog dialog = new JDialog(loginWindow, "Выберите роль", true); // Модальное диалоговое окно

        // Создаем кнопки
        JButton adminButton = new JButton("Администратор");
        JButton userButton = new JButton("Пользователь");

        // Добавляем обработчики событий для кнопок
        Application finalApp = app;
        adminButton.addActionListener(e -> {
            // Действия при нажатии кнопки "Администратор"
            System.out.println("Выбрана роль: Администратор");
            dialog.dispose(); // Закрываем диалоговое окно

            AdminDialog adminDialog = new AdminDialog(loginWindow, finalApp);
            adminDialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    saveData(finalApp);
                    exit(0);
                }
            });
            adminDialog.setVisible(true);

        });


        userButton.addActionListener(e -> {
            // Действия при нажатии кнопки "Пользователь"
            System.out.println("Выбрана роль: Пользователь");
            dialog.dispose(); // Закрываем диалоговое окно

            UserDialog userDialog = new UserDialog(loginWindow, finalApp);
            userDialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                  exit(0);
                }
            });

            userDialog.setVisible(true);
        });

        // Создаем панель и добавляем кнопки на нее
        JPanel panel = new JPanel();
        panel.add(adminButton);
        panel.add(userButton);

        // Добавляем панель на диалоговое окно
        dialog.add(panel);

        dialog.pack();
        dialog.setLocationRelativeTo(null); // Центрируем окно на экране
        dialog.setVisible(true);
    }

    private static Application loadData() {
        System.out.println("Загрузка списка электроприборов из файла:");
        try {
            FileInputStream fis = new FileInputStream(FILE_PATH);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Application app = (Application) ois.readObject();
            System.out.println("\nИнформация загружена!\n");
            return app;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
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