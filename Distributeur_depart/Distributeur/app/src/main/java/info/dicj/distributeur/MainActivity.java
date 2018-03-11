package info.dicj.distributeur;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import info.dicj.distributeur.Distributeur.Distributeur;
import info.dicj.distributeur.Distributeur.exception.AucunDistribuableException;
import info.dicj.distributeur.Distributeur.exception.AucunMelangeException;
import info.dicj.distributeur.Distributeur.exception.DebordementMelangeException;
import info.dicj.distributeur.Distributeur.*;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {

    private Distributeur distributeur;
    private String nom;

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
        } catch (AucunDistribuableException e) {
            e.printStackTrace();
        }
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


    public void onCheckedChanged(View view) {
            boolean checked = ((RadioButton) view).isChecked();
            switch(view.getId()) {
                case R.id.rdbtn1:
                    if (checked)
                    {
                        this.nom=((EditText)findViewById(R.id.edit_text)).getText().toString();
                        ((TextView)findViewById(R.id.textView3)).setText("Bonjour "+nom+", merci de votre enthousiasme!");
                        break;
                    }
                case R.id.rdbtn2:
                    if (checked)
                        this.nom=((EditText)findViewById(R.id.edit_text)).getText().toString();
                        ((TextView)findViewById(R.id.textView3)).setText("Bonjour "+nom+", dommage mais t'as pas le choix");
                        break;
            }
    }
    public void onCheckBoxChanged(View view) {
        if (((CheckBox) view).isChecked()) {
            ((Button) findViewById(R.id.btn_verser_prec)).setVisibility(GONE);
        } else {
            ((Button) findViewById(R.id.btn_verser_prec)).setVisibility(View.VISIBLE);
        }
    }

}
