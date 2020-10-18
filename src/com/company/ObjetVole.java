package com.company;

public class ObjetVole implements Comparable<ObjetVole> {

    private double value;
    private double weight;

    public ObjetVole(double weight, double value) {
        this.value = value;
        this.weight = weight;
    }

    public double getValue() {
        return value;
    }

    public double getWeight() {
        return weight;
    }

    public double getRatio() {
        return value/ weight;
    }

    @Override
    public int compareTo(ObjetVole objet)
    {
        if(getRatio()< objet.getRatio())return -1;
        else if (getRatio() == objet.getRatio())return 0;
        else return 1;

    }
}
