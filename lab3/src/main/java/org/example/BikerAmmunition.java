package org.example;

import java.io.Serializable;
import java.util.Objects;

public class BikerAmmunition implements Comparable<BikerAmmunition>, Serializable {

    private String title;
    private double weight;
    private double price;

    public BikerAmmunition() {    }

    public BikerAmmunition(String title, double weight, double price) {
        this.title = title;
        this.weight = weight;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BikerAmmunition that = (BikerAmmunition) o;
        return Double.compare(weight, that.weight) == 0 && Double.compare(price, that.price) == 0 && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, weight, price);
    }

    @Override
    public String toString() {
        return "BikerAmmunition{" +
                "title='" + title + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(BikerAmmunition ammunition) {
        return Double.compare(this.weight, ammunition.weight);
    }
}
