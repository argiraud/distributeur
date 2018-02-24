package info.dicj.distributeur.Distributeur;

import android.util.Log;

import java.util.ArrayList;

public class Melange implements Recette{
    
    private int nbBoissons;
    private final int MAX_BOISSON=2;
    private ArrayList<Boisson> boissons;
    private Saveur saveur;
    
    public Melange(){
        nbBoissons=0;
        boissons=new ArrayList<>();
        saveur = null;
    }
    
    public void ajouterBoisson(Boisson boisson){
        if (nbBoissons<MAX_BOISSON){
            boisson.ajouter();
            boissons.add(boisson);

        }
        else Log.i("DICJ", "Maximum de boissons atteint");
    }
    
    public void ajouterSaveur(Saveur saveur) {
        if (this.saveur == null){
            this.saveur = saveur;
            this.saveur.ajouter();
        }
        else Log.i("DICJ", "Maximum de saveur atteint");
    }
    
    public String getInformation(){
        String lesBoissons = new String();
        for (int i=0; i<boissons.size(); i++) {
            lesBoissons= boissons.get(i).toString();
        }
        if(this.saveur!=null) {
            return "Melange :\n- Boissons : " + lesBoissons + ")\n- Saveur : " + saveur.toString();
        }
        else{
            return "Melange : \n-Boissons : "+lesBoissons;
        }
    }
    
    public Recette getRecette(){
        return this;
    }
    
    public boolean estPret(){
       return !this.boissons.isEmpty();
    }


}

