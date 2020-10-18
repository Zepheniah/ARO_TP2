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

        Collections.sort(sac.ObjetVoles);
        Collections.reverse(sac.ObjetVoles);

        sac.borneInferieur = sac.calculBorneInf();

        BranchAndBound bab = new BranchAndBound(sac.borneInferieur);
        double Maxvalue = bab.branchAndBound(0, 0, sac.capacity, sac.ObjetVoles);
        affiche(bab.succes);
        //affiche(sac.ObjetVoles);
        System.out.println("Valeur max que le voleur a réussi à emporter : " + Maxvalue);
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

    public static void affiche(ArrayList<ObjetVole> ObjetVoles)
    {
        for(int i = 0 ; i<ObjetVoles.size();i++){
            ObjetVole obj = ObjetVoles.get(i);
            System.out.println("Valeur : " + obj.getValue() + ", Poids : " + obj.getWeight() + ", Ratio : " + obj.getRatio());

        }
        System.out.println(ObjetVoles.size());
    }

    public double calculBorneInf()
    {
        int currentCapacity = capacity;
        Iterator<ObjetVole> it = ObjetVoles.iterator();
        ObjetVole ov;

        while(it.hasNext())
        {
            ov = it.next();
            if(ov.getWeight() <= currentCapacity) {
                currentCapacity -= ov.getWeight();
                borneInferieur += ov.getValue();
            }
        }

        return borneInferieur;
    }
}
