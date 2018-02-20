package info.dicj.distributeur.Distributeur;

import android.util.Log;

import java.util.ArrayList;

public class Melange {
    
    private int nbBoissons;
    private final int MAX_BOISSON=2;
    private ArrayList<Boisson> boissons;
    private Saveur saveur;
    
    public Melange(){
        nbBoissons=0;
        boissons=new ArrayList<>();
    }
    
    public void ajouterBoisson(Boisson boisson){
        if (nbBoissons<MAX_BOISSON){
            boissons.add(boisson);
        }
        else Log.i("DICJ", "Maximum de boissons atteint");
    }
    
    public void ajouterSaveur(Saveur saveur){
        if (this.saveur==null)
            this.saveur=saveur;
        else Log.i("DICJ", "Maximum de saveur atteint");
    }
    
    public String getInformation(){
        String lesBoissons = new String();
        for (Boisson boisson:boissons) {
            lesBoissons=lesBoissons+boisson.toString();
        }
        return "Melange : -Boissons("+lesBoissons+")  -Saveur : "+saveur.toString();
    }
    
    public Recette getRecette(){
        return (Recette)this;
    }
    
    public boolean estPret(){
        if(boissons.size()>0){
            return true;
        }
        return false;
    }
}

