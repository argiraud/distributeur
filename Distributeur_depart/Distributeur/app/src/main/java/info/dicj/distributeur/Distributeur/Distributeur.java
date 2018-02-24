package info.dicj.distributeur.Distributeur;

import android.util.Log;

import java.util.ArrayList;

import info.dicj.distributeur.Distributeur.exception.AucunMelangeException;
import info.dicj.distributeur.Distributeur.exception.DebordementMelangeException;
import info.dicj.distributeur.Distributeur.impl.Fraise;
import info.dicj.distributeur.Distributeur.impl.Orangeade;
import info.dicj.distributeur.Distributeur.impl.Pepsi;
import info.dicj.distributeur.Distributeur.impl.Racinette;

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
        remplirDistributeur();
    }

    private void remplirDistributeur(){
        for (int i=0;i<10;i++){
            Boisson pepsi = new Pepsi();
            boissons.add(pepsi);
            Boisson orangeade = new Orangeade();
            boissons.add(orangeade);
            Boisson racinette = new Racinette();
            boissons.add(racinette);
            Boisson fraise = new Fraise();
            boissons.add(fraise);
        }

    }

    public Recette melangerRecette() throws AucunMelangeException {
        if (boissons.isEmpty()) throw new AucunMelangeException();
        else {
            for (Boisson bois : boissons) {
                melangeCourant.ajouterBoisson(bois);
            }
            for (Saveur sav : saveurs) {
                melangeCourant.ajouterSaveur(sav);
            }
            return this.melangeCourant;
        }
    }


    public void nouveauMelange(){
        this.melangePrecedent=this.melangeCourant;
        this.melangeCourant = null;
    }

    public Recette dupliquerMelange() throws AucunMelangeException {
            if (this.melangeCourant == null) {
                throw new AucunMelangeException();
            }
            else {
                this.melangePrecedent = this.melangeCourant;

                return this.melangeCourant;
            }
    }

    public void ajouterBoisson(String nom) throws DebordementMelangeException {
        if(boissons.size()<2){
            boissons.add(new Boisson(nom,"je suis la description de "+nom));
        }
        else{
            Log.i("DICJ", "Distributeur.ajouterBoisson: Erreur + de 2 boissons");
            throw new DebordementMelangeException();
        }
    }

    public void ajouterSaveur(String nom) throws DebordementMelangeException {
        if(!saveurs.isEmpty()){
            Log.i("DICJ", "Distributeur.ajouterSaveur: Erreur il y a déjà une saveur");
            throw new DebordementMelangeException();
        }
        else{
            saveurs.add(new Saveur(nom,"Je suis la description de "+nom));
        }
    }
}
