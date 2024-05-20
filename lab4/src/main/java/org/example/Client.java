package org.example;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Client {

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Клиент");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        JPanel topPanel = new JPanel();
        JButton btnSend = new JButton("Отправить");
        JPanel srcMatrixPanel = new JPanel();
        srcMatrixPanel.setLayout(new GridLayout(3,3));
        JPanel dstMatrixPanel = new JPanel();
        dstMatrixPanel.setLayout(new GridLayout(3,3));

        JTextField[] tfSrcMatrix = new JTextField[9];
        JTextField[] tfDstMatrix = new JTextField[9];
        for (int i = 0; i < 9; i++) {
            tfSrcMatrix[i] = new JTextField();
            tfSrcMatrix[i].setText(String.valueOf(i));
            srcMatrixPanel.add(tfSrcMatrix[i]);

            tfDstMatrix[i] = new JTextField();
            dstMatrixPanel.add(tfDstMatrix[i]);
        }
        srcMatrixPanel.setPreferredSize(new Dimension(100, 100));
        dstMatrixPanel.setPreferredSize(new Dimension(100, 100));
        topPanel.add(btnSend);
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(srcMatrixPanel, BorderLayout.WEST);
        frame.add(dstMatrixPanel, BorderLayout.EAST);
       // frame.pack();

        DecimalFormat formatter = new DecimalFormat("#.##");


        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Double> list = new ArrayList<>(9);
                int index=0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        Double v = Double.valueOf(tfSrcMatrix[index++].getText());
                        list.add(v);
                    }
                }
                String json = new Gson().toJson(list);
                System.out.println(json);
                try {
                    // Создаем сокет и подключаемся к серверу на localhost и порту 10000
                    Socket clientSocket = new Socket("localhost", 10000);
                    // Получаем потоки ввода и вывода для общения с сервером
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    // Отправляем строку на сервер
                    out.println(json);
                    // Получаем ответ от сервера
                    json = in.readLine();
                    Type listType = new TypeToken<ArrayList<Double>>() {}.getType();
                    if(json.equals("")) {
                        JOptionPane.showMessageDialog(null, "Матрица не имеет обратной");
                        for (int i = 0; i < 9; i++)
                            tfDstMatrix[i].setText(null);
                    }
                    else {
                        list = new Gson().fromJson(json, listType);
                        for (int i = 0; i < 9; i++) {
                            tfDstMatrix[i].setText(formatter.format(list.get(i)));
                        }
                    }
                    // Закрываем сокет
                    clientSocket.close();
                } catch (IOException ex) {
                    System.err.println("Ошибка при подключении к серверу: " + ex.getMessage());
                }
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Client::createAndShowGUI);
    }
}
