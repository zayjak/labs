package org.example;

import java.util.ArrayList;
import java.util.Objects;

public class Biker {
    private String name;
    private ArrayList<BikerAmmunition> ammunitionArrayList;

    public Biker(String name) {
        this.name = name;
        ammunitionArrayList = new ArrayList<>();
    }

    public void addAmmunition(BikerAmmunition ammunition) {
        ammunitionArrayList.add(ammunition);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<BikerAmmunition> getAmmunitionArrayList() {
        return ammunitionArrayList;
    }

    public void setAmmunitionArrayList(ArrayList<BikerAmmunition> ammunitionArrayList) {
        this.ammunitionArrayList = ammunitionArrayList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Biker biker = (Biker) o;
        return Objects.equals(name, biker.name) && Objects.equals(ammunitionArrayList, biker.ammunitionArrayList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ammunitionArrayList);
    }

    @Override
    public String toString() {
        return "Biker{" +
                "name='" + name + '\'' +
                ", ammunitionArrayList=" + ammunitionArrayList +
                '}';
    }
}
