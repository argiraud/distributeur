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
        pepsi = 10;
        fraise = 2;
        racinette = 2;
        orangeade = 2;
        bacon = 2;
        epice = 2;
        gingembre = 2;
    }

    public Recette melangerRecette() throws AucunMelangeException, AucunDistribuableException {
        if (melangeCourant == null) throw new AucunMelangeException();
        if (melangeCourant.getNbBoissons() == 0) throw new AucunDistribuableException();
        else {
            return melangeCourant.getRecette();
        }
    }


    public void nouveauMelange(){
        this.melangePrecedent=this.melangeCourant;
        this.melangeCourant = new Melange();
    }

    public Recette dupliquerMelange() throws AucunMelangeException, AucunDistribuableException, DebordementMelangeException {
        Melange melangeDuplique = new Melange();
        String nom = "";
        if (this.melangePrecedent == null) {
                throw new AucunMelangeException();
            }
        else {
            ArrayList<Boisson> boissons = melangePrecedent.getBoissons();
            for (int i = 0; i<boissons.size(); i++){
                nom = boissons.get(i).getNom();
                switch (nom) {
                    case "PEPSI":
                        if (pepsi == 0) {
                            throw new AucunDistribuableException();
                        }
                        melangeDuplique.ajouterBoisson(new Boisson(nom, "Je suis la description de " + nom,boissons.get(i).getQuantite()-1));
                        pepsi = pepsi - 1;
                        break;
                    case "ORANGEADE":
                        if (orangeade == 0) {
                            throw new AucunDistribuableException();
                        }
                        melangeDuplique.ajouterBoisson(new Boisson(nom, "Je suis la description de " + nom,boissons.get(i).getQuantite()-1));
                        orangeade = orangeade - 1;
                        break;
                    case "FRAISE":
                        if (fraise == 0) {
                            throw new AucunDistribuableException();
                        }
                        melangeDuplique.ajouterBoisson(new Boisson(nom, "Je suis la description de " + nom,boissons.get(i).getQuantite()-1));
                        fraise = fraise - 1;
                        break;
                    case "RACINETTE":
                        if (racinette == 0) {
                            throw new AucunDistribuableException();
                        }
                        melangeDuplique.ajouterBoisson(new Boisson(nom, "Je suis la description de " + nom,boissons.get(i).getQuantite()-1));
                        racinette = racinette - 1;
                        break;

                }
            }
            if (melangePrecedent.isSaveurAjoute()) {
                nom = melangePrecedent.getSaveur().getNom();
                switch (nom) {
                    case "GINGEMBRE":
                        if (gingembre == 0) {
                            throw new AucunDistribuableException();
                        }
                        melangeDuplique.ajouterSaveur(new Saveur(nom, "Je suis la description de " + nom));
                        gingembre = gingembre - 1;
                        break;
                    case "EPICE":
                        if (epice == 0) {
                            throw new AucunDistribuableException();
                        }
                        melangeDuplique.ajouterSaveur(new Saveur(nom, "Je suis la description de " + nom));
                        epice = epice - 1;
                        break;
                    case "BACON":
                        if (bacon == 0) {
                            throw new AucunDistribuableException();
                        }
                        melangeDuplique.ajouterSaveur(new Saveur(nom, "Je suis la description de " + nom));
                        bacon = bacon - 1;
                        break;
                }
            }
                return melangeDuplique.getRecette();
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

    public void ajouterSaveur(String nom) throws DebordementMelangeException, AucunDistribuableException {
        if (melangeCourant.isSaveurAjoute()){
            Log.i("DICJ", "Distributeur.ajouterBoisson: Erreur + de 1 saveur");
            throw new DebordementMelangeException();
        }
        else {
            switch (nom) {
                case "GINGEMBRE":
                    if (gingembre == 0) {
                        throw new AucunDistribuableException();
                    }
                    melangeCourant.ajouterSaveur(new Saveur(nom, "Je suis la description de " + nom));
                    gingembre = gingembre - 1;
                    break;
                case "EPICE":
                    if (epice == 0) {
                        throw new AucunDistribuableException();
                    }
                    melangeCourant.ajouterSaveur(new Saveur(nom, "Je suis la description de " + nom));
                    epice = epice - 1;
                    break;
                case "BACON":
                    if (bacon == 0) {
                        throw new AucunDistribuableException();
                    }
                    melangeCourant.ajouterSaveur(new Saveur(nom, "Je suis la description de " + nom));
                    bacon = bacon - 1;
                    break;
            }
        }
    }
}
