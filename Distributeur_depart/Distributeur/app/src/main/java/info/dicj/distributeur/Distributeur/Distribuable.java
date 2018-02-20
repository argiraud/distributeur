package info.dicj.distributeur.Distributeur;

public class Distribuable implements Produit{
    private int quantite;
    private String nom;
    private String description;
    public final int MAX=10;

    public Distribuable(int quantite, String nom,String description){
        this.quantite=quantite;
        this.nom=nom;
        this.description=description;

    }

    public String getNom(){
        return this.nom;
    }

    public String getDescription(){
        return this.description;
    }

    public int getQuantite(){
        return this.quantite;
    }

    public boolean estVide(){
        return quantite==0;
    }

    public void ajouter(){
        if(this.quantite==MAX){
            // lancer une excepetion ?
        }
        else{
            this.quantite++;
        }

    }

    public void consommer(){
        this.quantite--;
    }
}
