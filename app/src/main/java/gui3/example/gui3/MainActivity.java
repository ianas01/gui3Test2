package gui3.example.gui3;

import android.content.Intent;
import android.os.Bundle;

import com.example.babyneeds.R;

import gui3.example.gui3.data.DataBaseHandler;
import gui3.example.gui3.item.Item;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private AlertDialog dialog;
    private AlertDialog.Builder builder;
    private Button saveButton;
    DataBaseHandler dataBaseHandler;



    private EditText nom;
    private EditText prenom;
    private EditText cin;
    private EditText dateNaissance;
    private EditText postale;
    private EditText ville;
    private EditText pays;
    private EditText filiere;
    private EditText departement;
    private EditText etablisement;
    private EditText tutelle;




    private void checkIfDBIsEmpty() {

        if (dataBaseHandler.getItemsCount() > 0) {

            startActivity(new Intent(MainActivity.this, List_Activity.class));
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dataBaseHandler = new DataBaseHandler(this);
        checkIfDBIsEmpty();


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                popUpDialog();
            }
        });
    }

    private void popUpDialog() {

        builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.popup, null);
        nom = view.findViewById(R.id.nom);
        prenom = view.findViewById(R.id.prenom);
        dateNaissance = view.findViewById(R.id.date_de_naissance);
        cin = view.findViewById(R.id.cin);
        postale = view.findViewById(R.id.adresse_postale);
        ville = view.findViewById(R.id.ville);
        pays = view.findViewById(R.id.pays);
        filiere = view.findViewById(R.id.filiere);
        departement = view.findViewById(R.id.Département);
        etablisement = view.findViewById(R.id.établissement);
        tutelle = view.findViewById(R.id.Tutelle);


        saveButton = view.findViewById(R.id.saveButton);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveItem(view);
            }
        } );


        builder.setView(view);
        dialog = builder.create();
        dialog.show();

    }

    public void saveItem(View view) {

        if (!nom.getText().toString().isEmpty() &&   !prenom.getText().toString().isEmpty() && !cin.getText().toString().isEmpty() &&   !dateNaissance.getText().toString().isEmpty()
                &&   !postale.getText().toString().isEmpty()  &&   !ville.getText().toString().isEmpty()  &&   !pays.getText().toString().isEmpty()
                &&   !filiere.getText().toString().isEmpty()  &&   !departement.getText().toString().isEmpty()  &&   !etablisement.getText().toString().isEmpty()
                &&   !tutelle.getText().toString().isEmpty()) {
            Item item = new Item();
            item.setNom(nom.getText().toString().trim());
            item.setPrenom((prenom.getText().toString().trim()));
            item.setCIN(cin.getText().toString().trim());
            item.setDate_de_naissance((dateNaissance.getText().toString().trim()));
            item.setAdresse_postale((postale.getText().toString().trim()));
            item.setVille((ville.getText().toString().trim()));
            item.setPays((pays.getText().toString().trim()));
            item.setFiliére((filiere.getText().toString().trim()));
            item.setDépartement((departement.getText().toString().trim()));
            item.setEtablisement((etablisement.getText().toString().trim()));
            item.setTutelle((tutelle.getText().toString().trim()));
            dataBaseHandler.addItem(item);
            Snackbar.make(view, "Objets ajoutés avec succès!", Snackbar.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    dialog.dismiss();

                    startActivity(new Intent(MainActivity.this, List_Activity.class));
                }
            }, 1000);

        } else {
            Snackbar.make(view, "Tous les champs doivent etre remplis", Snackbar.LENGTH_SHORT).show();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

}