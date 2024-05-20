package org.example;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Application implements IApplication{

    private Biker biker;
    private ArrayList<BikerAmmunition> ammunitions;

    private Scanner scanner;
    public Application() {

        biker = new Biker("Иванов");
        scanner = new Scanner(System.in);
        ammunitions = new ArrayList<>();
        ammunitions.add(new BikerAmmunition("шлем", 0.6, 95.50));
        ammunitions.add(new BikerAmmunition("перчатки", 0.15, 155.25));
        ammunitions.add(new BikerAmmunition("штаны", 0.335, 235.25));
        ammunitions.add(new BikerAmmunition("куртка", 0.615, 425.25));
        ammunitions.add(new BikerAmmunition("комбинезон", 0.795, 950.25));
    }

    public void addAmmunition() {
        System.out.print("Введите название амуниции: ");
        String title = scanner.nextLine();
        System.out.print("Введите вес: ");
        Double weight = scanner.nextDouble();
        System.out.print("Введите цену: ");
        Double price = scanner.nextDouble();
        scanner.nextLine();
        ammunitions.add(new BikerAmmunition(title, weight, price));
    }

    public void printAmmunitionList() {
        ammunitions.forEach(System.out::println);
    }

    public void addAmmunitionToBiker() {
        System.out.println("Список имеющейся амуниции: ");
        for(int i=0; i<ammunitions.size();i++){
            System.out.println((1 + i + ". " + ammunitions.get(i)));
        }
        System.out.print("Делайте выбор: ");
        int opt = scanner.nextInt();
        scanner.nextLine();
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
        int opt = scanner.nextInt();
        scanner.nextLine();
        return opt;
    }

    public void sort() {
        System.out.println("\nСписок аммуниции до сортировки: ");
        ammunitions.forEach(System.out::println);
        System.out.println("\nСписок аммуниции после сортировки: ");
        ammunitions.stream().sorted().forEach(System.out::println);
    }

    public void filter() {
        System.out.print("Введите значение цены от: ");
        double priceFrom = scanner.nextDouble();
        System.out.print("Введите значение цены до: ");
        double priceTo = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("\nЭлементы амуниции, соответствующие заданному диапазону параметров цены: ");
        for(BikerAmmunition a : ammunitions)
            if(a.getPrice() >= priceFrom && a.getPrice() <= priceTo)
                System.out.println(a);
    }
}
