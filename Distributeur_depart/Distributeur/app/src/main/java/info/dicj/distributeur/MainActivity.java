package info.dicj.distributeur;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import info.dicj.distributeur.Distributeur.Distributeur;
import info.dicj.distributeur.Distributeur.exception.AucunDistribuableException;
import info.dicj.distributeur.Distributeur.exception.AucunMelangeException;
import info.dicj.distributeur.Distributeur.exception.DebordementMelangeException;
import info.dicj.distributeur.Distributeur.*;

public class MainActivity extends AppCompatActivity {

    private Distributeur distributeur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.distributeur);

         distributeur = new Distributeur();

        Log.i("DICJ", "MainActivity.oncreate");
    }

    public void reverser(View view) {

        Log.i("DICJ", "MainActivity.reverser");


        try {
            distributeur.dupliquerMelange();
            verser(view);
        }
        catch (AucunMelangeException e) {
            e.printStackTrace();
        }
       /* catch (AucunDistribuableException e) {
            e.printStackTrace();
        }
        catch (DebordementMelangeException e) {
            e.printStackTrace();
        }
*/

    }

    public void verser(View view) {

        Log.i("DICJ", "MainActivity.verser");


        try {

            afficherRecette(distributeur.melangerRecette());

        } catch (AucunMelangeException e) {
            e.printStackTrace();
        }
        /*catch (AucunDistribuableException e) {
            e.printStackTrace();
        }
        */

    }


    public void ajouterSaveur(View view) {

        Log.i("DICJ", "MainActivity.ajouterSaveur");


        try {
            switch(view.getId()){

                case R.id.sEpice:       distributeur.ajouterSaveur("EPICE");
                                        break;
                case R.id.sGingembre:   distributeur.ajouterSaveur("GINGEMBRE");
                                        break;
                case R.id.sBacon:       distributeur.ajouterSaveur("BACON");
                                        break;
            }
        } /*
        catch (AucunDistribuableException e) {
            e.printStackTrace();
        }*/
        catch (DebordementMelangeException e) {
            e.printStackTrace();
        }

    }

    public void ajouterBoisson(View view) {

        Log.i("DICJ", "MainActivity.ajouterBoisson");


        try {
            switch(view.getId()){
                case R.id.bPepsi:       distributeur.ajouterBoisson("PEPSI");
                                        break;
                case R.id.bOrangeade:   distributeur.ajouterBoisson("ORANGEADE");
                                        break;
                case R.id.bRacinette:   distributeur.ajouterBoisson("RACINETTE");
                                        break;
                case R.id.bFraise:      distributeur.ajouterBoisson("FRAISE");
                                        break;
            }
        } /*catch (AucunDistribuableException e) {
            e.printStackTrace();
        } */
        catch (DebordementMelangeException e) {
            e.printStackTrace();
        }

    }

    public void nouveau(View view) {

        Log.i("DICJ", "MainActivity.nouveau");


        distributeur.nouveauMelange();

    }


    public void afficherRecette(Recette recette){


        Context context = getApplicationContext();
        CharSequence information = recette.getInformation();
        int duree = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, information, duree);
        toast.show();
        Log.i("DICJ", "MainActivity.afficherRecette"+information);
    }

    public Context getContext(){
        return getApplicationContext();
    }

}
