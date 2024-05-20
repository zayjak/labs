package org.example;
import javax.swing.*;
import java.awt.*;

public class RecordEditDialog extends JDialog {
    private JTextField fdTitle;


    private JSpinner fdWeight;
    private JSpinner fdPrice;

    private AmmunitionModel model;
    public RecordEditDialog(Frame owner, AmmunitionModel model) {
        super(owner, "Добавление записи", true); // Модальное диалоговое окно

        // Создание компонентов
        fdTitle = new JTextField(20);
        fdWeight = new JSpinner(new SpinnerNumberModel(0, 0, 10000, 0.5));
        fdPrice = new JSpinner(new SpinnerNumberModel(0, 0, 10000, 0.5));

        // Расположение компонентов
        setLayout(new GridLayout(5, 2)); // Добавляем еще одну строку для кнопки "Отмена"
        add(new JLabel("Название амуниции:"));
        add(fdTitle);
        add(new JLabel("Вес амуниции:"));
        add(fdWeight);
        add(new JLabel("Цена амуниции:"));
        add(fdPrice);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            // Обработка введенных данных
            String title = fdTitle.getText();
            double weight = (double) fdWeight.getValue();
            double price = (double) fdPrice.getValue();

            if(title.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Поле 'Название' должно быть заполнено");
                return;
            }
            if (weight == 0) {
                JOptionPane.showMessageDialog(null, "Поле 'Вес амуниции' не может содержать значение 0");
                return;
            }
            if(price==0) {
                JOptionPane.showMessageDialog(null, "Поле 'Цена' не может содержать значение 0");
                return;
            }
            BikerAmmunition a;
            a = new BikerAmmunition(title, weight, price);
            model.addAmmunition(a);

            // Закрытие диалогового окна
            setVisible(false);
        });
        add(okButton);

        // Кнопка "Отмена"
        JButton cancelButton = new JButton("Отмена");
        cancelButton.addActionListener(e -> {
            // Закрытие диалогового окна без сохранения данных
            setVisible(false);
        });
        add(cancelButton);

        pack();
        setLocationRelativeTo(owner);
    }
}