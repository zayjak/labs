package org.example;

public interface IApplication {
    public void addAmmunition() throws AmmunitionException;
    public void printAmmunitionList();
    public void addAmmunitionToBiker();
    public void calcPrice();
    public int showMenu();

    public void sort();

    public void filter() throws AmmunitionException;
}
