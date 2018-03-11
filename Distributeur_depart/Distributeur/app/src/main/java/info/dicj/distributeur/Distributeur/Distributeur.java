package info.dicj.distributeur.Distributeur;

import android.util.Log;

import java.util.ArrayList;

import info.dicj.distributeur.Distributeur.exception.AucunDistribuableException;
import info.dicj.distributeur.Distributeur.exception.AucunMelangeException;
import info.dicj.distributeur.Distributeur.exception.DebordementMelangeException;
import info.dicj.distributeur.Distributeur.impl.Fraise;
import info.dicj.distributeur.Distributeur.impl.Orangeade;
import info.dicj.distributeur.Distributeur.impl.Pepsi;
import info.dicj.distributeur.Distributeur.impl.Racinette;

public class Distributeur {

    private int pepsi = 0;
    private int fraise = 0;
    private int racinette = 0;
    private int orangeade = 0;
    private int bacon = 0;
    private int epice = 0;
    private int gingembre = 0;
    private Melange melangePrecedent;
    private Melange melangeCourant;

    public Distributeur(){
        this.melangeCourant = new Melange();
        this.melangePrecedent = null;
        remplirDistributeur();
    }

    private void remplirDistributeur(){
        this.pepsi = 2;
        fraise = 2;
        racinette = 2;
        orangeade = 2;
        bacon = 2;
        epice = 2;
        gingembre = 2;
    }

    public Recette melangerRecette() throws AucunMelangeException {
        if (melangeCourant == null) throw new AucunMelangeException();
        else {
            return melangeCourant.getRecette();
        }
    }


    public void nouveauMelange(){
        this.melangePrecedent=this.melangeCourant;
        this.melangeCourant = new Melange();
    }

    public Recette dupliquerMelange() throws AucunMelangeException {
            if (this.melangePrecedent == null) {
                throw new AucunMelangeException();
            }
            else {
                this.melangeCourant = this.melangePrecedent;

                return this.melangeCourant.getRecette();
            }
    }

    public void ajouterBoisson(String nom) throws DebordementMelangeException, AucunDistribuableException {
        if (melangeCourant.getNbBoissons() == 2){
            Log.i("DICJ", "Distributeur.ajouterBoisson: Erreur + de 2 boissons");
            throw new DebordementMelangeException();
        }
        else {
            switch (nom) {
                case "PEPSI":
                    if (pepsi == 0) {
                        throw new AucunDistribuableException();

                    }
                    melangeCourant.ajouterBoisson(new Boisson(nom, "Je suis la description de " + nom));
                    pepsi = pepsi - 1;
                    break;
                case "ORANGEADE":
                    if (orangeade == 0) {
                        throw new AucunDistribuableException();
                    }
                    melangeCourant.ajouterBoisson(new Boisson(nom, "Je suis la description de " + nom));
                    orangeade = orangeade - 1;
                    break;
                case "FRAISE":
                    if (fraise == 0) {
                        throw new AucunDistribuableException();
                    }
                    melangeCourant.ajouterBoisson(new Boisson(nom, "Je suis la description de " + nom));
                    fraise = fraise - 1;
                    break;
                case "RACINETTE":
                    if (racinette == 0) {
                        throw new AucunDistribuableException();
                    }
                    melangeCourant.ajouterBoisson(new Boisson(nom, "Je suis la description de " + nom));
                    racinette = racinette - 1;
                    break;
            }
        }
    }

    public void ajouterSaveur(String nom) throws DebordementMelangeException {
            melangeCourant.ajouterSaveur(new Saveur(nom,"Je suis la description de "+nom));
            switch (nom){
                case "gingembre":
                    if (gingembre == 0){

                    }
                    gingembre = gingembre -1;
                    break;
                case "epice":
                    if (epice == 0){

                    }
                    epice = epice -1;
                    break;
                case "bacon":
                    if (bacon == 0){

                    }
                    bacon = bacon-1;
                    break;
            }


    }
}
