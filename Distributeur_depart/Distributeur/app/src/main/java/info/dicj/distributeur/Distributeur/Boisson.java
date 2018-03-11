package info.dicj.distributeur.Distributeur;

public class Boisson extends Distribuable {

    public Boisson(String nom, String description){
        super(0,nom,description);
    }

    public String toString(){
        return "Boisson: "+super.getNom()+" Description: "+super.getDescription()+" Quantité: "+super.getQuantite();
    }
}
