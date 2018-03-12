package info.dicj.distributeur;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
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
    private static final int ID_NOTIFICATION = 1234;
    private NotificationManager nm;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.distributeur);

         distributeur = new Distributeur();

        Log.i("DICJ", "MainActivity.oncreate");
        nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

    }
    public void creerNotification(View view) {

        // Cette section sera revue lors de la semaine #8
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Notification n  = new Notification.Builder(this)
                .setContentTitle("Félicitation !!!")
                .setContentText("C'est un bien beau mélange")
                .setPriority(Notification.PRIORITY_HIGH)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pIntent)
                .setNumber(count++)
                .setSound(soundUri)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)

                .build();
        try{
            nm.notify(ID_NOTIFICATION, n);
        }
        catch(Exception e){
            Log.i("DICJ", e.getMessage(), e);
        }
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
        catch (AucunDistribuableException e) {
            e.printStackTrace();
        }
        catch (DebordementMelangeException e) {
            e.printStackTrace();
        }
    }

    public void verser(View view) {

        Log.i("DICJ", "MainActivity.verser");
        this.creerNotification(view);

        try {

            afficherRecette(distributeur.melangerRecette());

        } catch (AucunMelangeException e) {
            e.printStackTrace();
        }
        catch (AucunDistribuableException e) {
            e.printStackTrace();
        }
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
        }
        catch (AucunDistribuableException e) {
            e.printStackTrace();
        }
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
