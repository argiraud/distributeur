package info.dicj.distributeur.Distributeur;

public class Saveur extends Distribuable {
    public Saveur(String nom,String description){
        super(0,nom,description);
    }
    public String toString(){
        return "Saveur: "+super.getNom()+" Description:"+super.getDescription()+" Quantité: "+super.getQuantite();
    }
}
