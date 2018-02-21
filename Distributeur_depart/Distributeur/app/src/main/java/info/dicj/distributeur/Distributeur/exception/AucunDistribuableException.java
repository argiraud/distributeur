package info.dicj.distributeur.Distributeur.exception;

public class AucunDistribuableException extends Exception {
    public AucunDistribuableException(){
        System.out.println("Il n'y a pas de distribuable");
    }
}
