package info.dicj.distributeur.Distributeur;

import android.util.Log;

import java.util.ArrayList;
import java.util.Objects;

public class Melange implements Recette{
    
    private int nbBoissons;
    private boolean saveurAjoute;
    private final int MAX_BOISSON=2;
    private ArrayList<Boisson> boissons;
    private Saveur saveur;
    
    public Melange(){
        nbBoissons=0;
        saveurAjoute = false;
        boissons=new ArrayList<>();
        saveur = null;
    }
    
    public void ajouterBoisson(Boisson boisson){
        if (nbBoissons<MAX_BOISSON){
            boisson.ajouter();

            if (boissons.isEmpty())
                boissons.add(boisson);

            else {
                if (!Objects.equals(boisson.getNom(), boissons.get(0).getNom()))
                    boissons.add(boisson);

                else
                        boissons.get(0).ajouter();
            }
            nbBoissons++;
        }
        else Log.i("DICJ", "Maximum de boissons atteint");
    }
    
    public void ajouterSaveur(Saveur saveur) {
        if (!saveurAjoute){
            this.saveur = saveur;
            this.saveur.ajouter();
            saveurAjoute = true;
        }
        else Log.i("DICJ", "Maximum de saveur atteint");
    }
    
    public String getInformation(){
        StringBuilder lesBoissons = new StringBuilder();
        for (int i=0; i<boissons.size(); i++) {
            lesBoissons.append("  ").append(boissons.get(i).toString());
        }
        if(this.saveur!=null)
            return "Melange :\n- Boissons : " + lesBoissons + ")\n- Saveur : " + saveur.toString();

        else
            return "Melange : \n- Boissons : " + lesBoissons;

    }

    public int getNbBoissons() {
       return nbBoissons;
    }

    public boolean isSaveurAjoute() {
        return saveurAjoute;
    }

    public Recette getRecette(){
        return this;
    }

    public ArrayList<Boisson> getBoissons(){
        return  boissons;
    }

    public Saveur getSaveur(){
        return  saveur;
    }
    
    public boolean estPret(){
       return !this.boissons.isEmpty();
    }


}

