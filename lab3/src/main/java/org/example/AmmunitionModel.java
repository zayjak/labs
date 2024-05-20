package org.example;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AmmunitionModel extends AbstractTableModel {

    private final String[] columnNames = {"Название", "Вес", "Цена"};
    private final Application app;
    private final Boolean isEditable;
    private ArrayList<BikerAmmunition> list;
    public AmmunitionModel(Application app, Boolean isEditable) {
        this.app = app;
        this.isEditable = isEditable;
        if(isEditable) {
            list = app.getAmmunitions();
        }
        else {
            list = new ArrayList<>(app.getAmmunitions());
        }
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int i) {
        return columnNames[i];
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        BikerAmmunition a = this.list.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> a.getTitle();
            case 1 -> a.getWeight();
            case 2 -> a.getPrice();
            default -> null;
        };
    }

    @Override
    public void setValueAt(Object o, int rowIndex, int columnIndex) {
        BikerAmmunition a = list.get(rowIndex);

        switch (columnIndex) {
            case 0:
                a.setTitle(o.toString());
                break;
            case 1:
                a.setWeight(Double.valueOf(o.toString()));
                break;
            case 2:
                a.setPrice(Double.valueOf(o.toString()));
                break;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return isEditable;
    }

    public void addAmmunition(BikerAmmunition bikerAmmunition) {
        list.add(bikerAmmunition);
        fireTableDataChanged();
    }

    public void delAmmunition(int rowIndex) {
        if(rowIndex<=0)
            return;
        list.remove(rowIndex);
        fireTableDataChanged();
    }

    public BikerAmmunition getBikerAmmunition(int rowIndex) {
        if(rowIndex<=0)
            return null;
        return list.get(rowIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex >= 1 && columnIndex <= 2) {
            return Double.class;
        }
        return String.class;
    }

    public void filterUnits(Integer start, Integer end) {
        if(start == null || end == null)
            list = new ArrayList<>(app.getAmmunitions());
        else {
            list.clear();
            for (BikerAmmunition a : app.getAmmunitions()) {
                if (a.getPrice() >= start && a.getPrice()<=end) {
                    list.add(a);
                }
            }
        }
        fireTableDataChanged();
    }
}
