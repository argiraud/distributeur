package info.dicj.distributeur.Distributeur;

import android.util.Log;

import java.util.ArrayList;

public class Distributeur {

    private ArrayList<Boisson> boissons;
    private ArrayList<Saveur>saveurs;
    private Melange melangePrecedent;
    private Melange melangeCourant;

    public Distributeur(){
        this.melangeCourant = new Melange();
        this.melangePrecedent = new Melange();
        this.boissons = new ArrayList<>();
        this.saveurs = new ArrayList<>();
    }

    private void remplirDistributeur(){

    }

    public Recette melangerRecette(){
        for(Boisson bois : boissons){
            melangeCourant.ajouterBoisson(bois);
        }
        for(Saveur sav : saveurs){
            melangeCourant.ajouterSaveur(sav);
        }
        return (Recette)melangeCourant;
    }


    public void nouveauMelange(){
        this.melangePrecedent=this.melangeCourant;
        this.melangeCourant = null;
    }

    public Recette dupliquerMelange(){
        this.melangePrecedent = this.melangeCourant;

    }

    public void ajouterBoisson(String nom){
        if(boissons.size()<2){
            boissons.add(new Boisson(nom,"je suis la description de "+nom));
        }
        else{
            Log.i("DICJ", "Distributeur.ajouterBoisson: Erreur + de 2 boissons");
            //lancé une exception
        }
    }

    public void ajouterSaveur(String nom){
        if(!saveurs.isEmpty()){
            Log.i("DICJ", "Distributeur.ajouterSaveur: Erreur il y a déjà une saveur");
            //lancé une exception
        }
        else{
            saveurs.add(new Saveur(nom,"Je suis la description de "+nom));
        }
    }
}
