package org.example;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Application implements IApplication, Serializable {
    private Biker biker;
    private ArrayList<BikerAmmunition> ammunitions;

    public Application() {
        ammunitions = new ArrayList<>();
    }

    public ArrayList<BikerAmmunition> getAmmunitions() {
        return ammunitions;
    }

    public void addAmmunition() throws AmmunitionException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название амуниции: ");
        String title = scanner.nextLine();
        System.out.print("Введите вес: ");
        double weight = scanner.nextDouble();
        if(weight<=0.0)
            throw new AmmunitionException("Вес должен быть больше 0");
        System.out.print("Введите цену: ");
        double price = scanner.nextDouble();
        if (price <= 0.0) {
            throw new AmmunitionException("Цена должена быть больше 0");
        }

        scanner.nextLine();
        ammunitions.add(new BikerAmmunition(title, weight, price));
    }

    public void printAmmunitionList() {
        ammunitions.forEach(System.out::println);
    }

    public void addAmmunitionToBiker() {
        int opt;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Список имеющейся амуниции: ");
        for(int i=0; i<ammunitions.size();i++){
            System.out.println((1 + i + ". " + ammunitions.get(i)));
        }
        System.out.print("Делайте выбор: ");

        try {
            opt = scanner.nextInt();
            scanner.nextLine();


        } catch (Exception ex) {
            System.out.println("Введено некорректное значение");
            scanner.nextLine();
            return;
        }
        biker.addAmmunition(ammunitions.get(opt - 1));
    }

    public void calcPrice() {

        double price = 0.0;
        for(BikerAmmunition a : ammunitions) {
            price += a.getPrice();
        }
        System.out.println("Стоимость амуниции: " + price);
    }

    public int showMenu() {
        int opt;
        String[] options = {
                "\n1 - Добавить амуницию",
                "2 - Вывести список амуниции",
                "3 - Добавить амуницию к мотоциклисту",
                "4 - Подсчитать стоимость амуниции",
                "5 - Провести сортировку амуниции на основе веса",
                "6 - Найти элементы амуниции, соответствующие заданному диапазону параметров цены",
                "0 - Выход"
        };
        Arrays.stream(options).forEach(System.out::println);
        System.out.print("Делайте выбор: ");
        Scanner scanner = new Scanner(System.in);
        try {
            opt = scanner.nextInt();
            scanner.nextLine();
            return opt;
        } catch (Exception ex) {
            System.out.println("Введите число от 0 до " + (options.length-1));
            scanner.nextLine();
            return -1;
        }
    }

    public void sort() {
        System.out.println("\nСписок аммуниции до сортировки: ");
        ammunitions.forEach(System.out::println);
        System.out.println("\nСписок аммуниции после сортировки: ");
        ammunitions.stream().sorted().forEach(System.out::println);
    }

    public void filter() throws AmmunitionException {
        double priceFrom;
        double priceTo;
        System.out.print("Введите значение цены от: ");
        Scanner scanner = new Scanner(System.in);


        try {
            priceFrom = scanner.nextDouble();
        } catch (Exception ex) {
            System.out.println("Введено некорректное значение");
            return;
        }

        if (priceFrom <= 0.0) {
            throw new AmmunitionException("Цена должена быть больше 0");
        }
        System.out.print("Введите значение цены до: ");

        try {
            priceTo = scanner.nextDouble();
        } catch (Exception ex) {
            System.out.println("Введено некорректное значение");
            return;
        }

        if (priceTo <= 0.0) {
            throw new AmmunitionException("Цена должена быть больше 0");
        }
        scanner.nextLine();
        System.out.println("\nЭлементы амуниции, соответствующие заданному диапазону параметров цены: ");
        for(BikerAmmunition a : ammunitions)
            if(a.getPrice() >= priceFrom && a.getPrice() <= priceTo)
                System.out.println(a);
    }
}
