package com.company;

import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

public class SacADos {

    private ArrayList<ObjetVole> ObjetVoles;
    private int capacity;
    private double borneInferieur;

    public SacADos() {
        ObjetVoles = new ArrayList<ObjetVole>(0);
    }

    public ArrayList<ObjetVole> getObjetVoles() {
        return ObjetVoles;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getBorneInferieur() {
        return borneInferieur;
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        System.setIn(new FileInputStream(args[0]));
        SacADos sac = new SacADos();

        sac.fill();
        preparation_liste(sac.ObjetVoles);
        sac.borneInferieur = sac.calculBorneInf();

        AlgoBranch branch = new AlgoBranch(sac.borneInferieur);
        double Maxvalue = branch.branchAndBound(0, 0, sac.capacity, sac.ObjetVoles);

        System.out.println("Valeur max que le voleur a réussi à emporter : " + Maxvalue);
    }

    /**
     * Trie la liste dans l'ordre décroissant des rations (Comparator ObjetVole)
     */
    public static void preparation_liste(ArrayList<ObjetVole> ObjetsVoles){
        Collections.sort(ObjetsVoles);
        Collections.reverse(ObjetsVoles);
    }


    /**
     *
     * Parcours les objets volable(la liste étant trié de maniere décroissante sur les valeur de ration) et met le plus d'objet possible
     * afin d'obtenir l'une des solution possible pour determiner une borne inférieur.
     */

    public double calculBorneInf()
    {
        int currentCapacity = capacity;

        for(int i = 0;i<ObjetVoles.size();i++){
            if(ObjetVoles.get(i).getWeight() <= currentCapacity){
                currentCapacity -= ObjetVoles.get(i).getWeight();
                borneInferieur += ObjetVoles.get(i).getValue();
            }
        }
        return borneInferieur;
    }

    public void fill()
    {
        Scanner scan = new Scanner(System.in);
        if(scan.hasNext())
            capacity = scan.nextInt();

        scan.nextLine();
        while (scan.hasNext())
        {
            ObjetVole obj = new ObjetVole(scan.nextDouble(), scan.nextDouble());
            ObjetVoles.add(obj);
        }
    }
}
