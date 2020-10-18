package com.company;
import java.util.ArrayList;
import java.util.LinkedList;


public class BranchAndBound {
    private double maxValue;
    public ArrayList<ObjetVole> succes;
    boolean test = false;


    public BranchAndBound(double maxValue) {
        this.maxValue = maxValue;
        succes = new ArrayList<>();
    }


    public double getMaxValue() {
        return maxValue;
    }

    /* height = hauteur actuelle du noeud */
    public double branchAndBound(int height, double value, double capacity, ArrayList<ObjetVole> objetVoles)
    {
        double borneSup = value + calculBorneSup(capacity, objetVoles, height);
        /* Si la hauteur est égale au nombre d'objets, alors on a fini */
        if(height == objetVoles.size()) {
            if(value > maxValue)
                maxValue = value;
        }

        /* Sinon on construit les noeuds suivants recursivement */
        else if(borneSup > maxValue)
        {

            int next_node = height +1;
            if(objetVoles.get(height).getWeight() <= capacity){
                double new_value = objetVoles.get(height).getValue() + value;
                double updatated_capacity = capacity - objetVoles.get(height).getWeight();
                branchAndBound(next_node, new_value, updatated_capacity, objetVoles);
            }
            branchAndBound(next_node, value, capacity, objetVoles);
        }

        return maxValue;
    }

    public double calculBorneSup(double capacity, ArrayList<ObjetVole> objetVoles, int height)
    {
        double borneSuperieur = 0;

        ObjetVole objet;
        ArrayList<ObjetVole>test = new ArrayList<>();
        for(int i = height ; i < objetVoles.size(); i++)
        {
            objet = objetVoles.get(i);
            if(objet.getWeight() <= capacity)
            {

                capacity -= objet.getWeight();
                borneSuperieur += objet.getValue();

            }

            else if(capacity > 0)
            {
                borneSuperieur += (double)(capacity)*objet.getRatio();
                capacity = 0;
                break;

            }
            if(capacity == 0)break;
        }
        return borneSuperieur;
    }

}
