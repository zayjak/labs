package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDialog extends JDialog {
    private final JTable unitTable;
    private final JButton addUnitButton;
    private final JButton delUnitButton;
    private Application app;
    public AdminDialog(Frame owner, Application app) {
        super(owner, "Мотоциклист. Режим администратора", true); // Заголовок диалогового окна
        this.app = app;

        // Создание модели таблицы
        AmmunitionModel model = new AmmunitionModel(app, true);
        // создание таблицы
        unitTable = new JTable(model);

        // Создание кнопок
        addUnitButton = new JButton("Добавить");
        delUnitButton = new JButton("Удалить");

        // Добавление компонентов на диалоговое окно
        setLayout(new BorderLayout());

        // Добавление текстовых полей на диалоговое окно
        JPanel inputPanel = new JPanel();
        inputPanel.add(addUnitButton);
        inputPanel.add(delUnitButton);

        add(inputPanel, BorderLayout.NORTH);

        add(new JScrollPane(unitTable), BorderLayout.CENTER);

        // Обработчики событий для кнопок
        addUnitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RecordEditDialog dialog = new RecordEditDialog(null, model);
                dialog.setVisible(true);
            }
        });
        delUnitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowIndex = unitTable.getSelectedRow();
                if(rowIndex > -1) {
                    int reply = JOptionPane.showConfirmDialog(null, "Удалить запись?", "Внимание", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        model.delAmmunition(rowIndex);
                    }
                }
            }
        });
        pack(); // Установка размеров окна
        setLocationRelativeTo(owner); // Центрирование окна относительно родительского фрейма
    }

}