package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserDialog extends JDialog {
    private final JTable unitTable;
    private final JButton searchButton;
    private final JButton allDataButton;
    private JTextField fdStart;
    private JTextField fdEnd;
    private Application app;
    public UserDialog(Frame owner, Application app) {
        super(owner, "Мотоциклист. Режим пользователя", true);
        this.app = app;

        // Создание модели таблицы
        AmmunitionModel model = new AmmunitionModel(app, false);
        // создание таблицы

        unitTable = new JTable(model);
        unitTable.setAutoCreateRowSorter(true);

        // Создание кнопок
        searchButton = new JButton("Найти");
        allDataButton = new JButton("Все записи");

        // Создание текстовых полей
        fdStart = new JTextField(10); // 10 - это ширина поля в символах
        fdEnd = new JTextField(10);

        // Добавление компонентов на диалоговое окно
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Цена от :"));
        inputPanel.add(fdStart);
        inputPanel.add(new JLabel("до:"));
        inputPanel.add(fdEnd);
        inputPanel.add(searchButton);
        inputPanel.add(allDataButton);

        add(inputPanel, BorderLayout.NORTH);

        add(new JScrollPane(unitTable), BorderLayout.CENTER);

        // Обработчики событий для кнопок
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int start, end;
                try {
                    start = Integer.parseInt(fdStart.getText());
                    end = Integer.parseInt(fdEnd.getText());
                    model.filterUnits(start, end);
                } catch(NumberFormatException ignored) {
                    JOptionPane.showMessageDialog(null, "Введены некорректные парамеры");
                }
            }
        });
        allDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.filterUnits(null, null);
            }
        });
        pack(); // Установка размеров окна
        setLocationRelativeTo(owner); // Центрирование окна относительно родительского фрейма
    }
}