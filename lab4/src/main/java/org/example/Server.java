package org.example;

import java.io.*;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.la4j.LinearAlgebra;
import org.la4j.Matrix;
import org.la4j.matrix.dense.Basic2DMatrix;


class Server {
    public static void main(String[] args) {
        // Создаем сокет сервера
        try (ServerSocket serverSocket = new ServerSocket(10000)) {
            System.out.println("Сервер запущен и ожидает подключений...");

            while (true) {
                // Принимаем подключение от клиента
                Socket clientSocket = serverSocket.accept();
                System.out.println("Подключился клиент: " + clientSocket.getInetAddress());

                // Получаем потоки ввода и вывода для общения с клиентом
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Читаем сообщение от клиента
                String json = in.readLine();
                System.out.println("Получено сообщение от клиента: " + json);

                Type listType = new TypeToken<ArrayList<Double>>() {}.getType();
                ArrayList<Double> list  = new Gson().fromJson(json, listType);

                int index=0;
                double [][] matrix = new double [3][3];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        matrix[i][j] = list.get(index++);
                    }
                }

                try {
                    Matrix a = new Basic2DMatrix(matrix);

                    Matrix b = a.withInverter(LinearAlgebra.InverterFactory.GAUSS_JORDAN).inverse();

                    list.clear();
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            list.add(b.get(i, j));
                        }
                    }
                    // Отправляем ответ клиенту
                    json = new Gson().toJson(list);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    json = "";
                }
                out.println(json);

                // Закрываем ресурсы
                in.close();
                out.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            System.err.println("Ошибка при работе сервера: " + e.getMessage());
        }
    }
    static ArrayList<Double> inversion(double [][]matrix, int N)
    {
        double temp;

        double [][] E = new double [N][N];

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
            {
                E[i][j] = 0f;

                if (i == j)
                    E[i][j] = 1f;
            }

        for (int k = 0; k < N; k++)
        {
            temp = matrix[k][k];

            for (int j = 0; j < N; j++)
            {
                matrix[k][j] /= temp;
                E[k][j] /= temp;
            }

            for (int i = k + 1; i < N; i++)
            {
                temp = matrix[i][k];
                for (int j = 0; j < N; j++)
                {
                    matrix[i][j] -= matrix[k][j] * temp;
                    E[i][j] -= E[k][j] * temp;
                }
            }
        }

        for (int k = N - 1; k > 0; k--)
        {
            for (int i = k - 1; i >= 0; i--)
            {
                temp = matrix[i][k];

                for (int j = 0; j < N; j++)
                {
                    matrix[i][j] -= matrix[k][j] * temp;
                    E[i][j] -= E[k][j] * temp;
                }
            }
        }

        ArrayList<Double> list = new ArrayList<>();
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                list.add(E[i][j]);
        return list;
    }
}