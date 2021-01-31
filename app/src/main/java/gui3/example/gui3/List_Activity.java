package gui3.example.gui3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.babyneeds.R;

import gui3.example.gui3.data.DataBaseHandler;
import gui3.example.gui3.item.Item;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class List_Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecycleViewAdapter recycleViewAdapter;
    private DataBaseHandler dataBaseHandler;
    private List<Item> itemList;
    private FloatingActionButton fab;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    private Button saveButton;
    private Button deleteButton;


    private EditText nom_editText;
    private EditText prenom_editText;
    private EditText CIN_editText;
    private EditText dateDeNaissance_editText;
    private EditText addressePostale_editText;
    private EditText ville_editText;
    private EditText pays_editText;
    private EditText filiere_editText;
    private EditText departement_editText;
    private EditText etablisement_editText;
    private EditText tutelle_editText;



/*
    private EditText name_editText;
    private EditText color_editText;
    private EditText size_editText;
    private EditText quantity_editText;
*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_);


        fab = findViewById(R.id.fab2);


        recyclerView = findViewById(R.id.recycleView);

        dataBaseHandler = new DataBaseHandler(this);





        itemList = new ArrayList<>();
         dataBaseHandler = new DataBaseHandler(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemList = dataBaseHandler.getAllItems();




        recycleViewAdapter = new RecycleViewAdapter(this, itemList);
        recyclerView.setAdapter(recycleViewAdapter);


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


        nom_editText =  view.findViewById(R.id.nom);
        prenom_editText = view.findViewById(R.id.prenom);
        CIN_editText = view.findViewById(R.id.cin);
        dateDeNaissance_editText  =  view.findViewById(R.id.date_de_naissance);
        addressePostale_editText  =  view.findViewById(R.id.adresse_postale);
        ville_editText  =  view.findViewById(R.id.ville);
        pays_editText  =  view.findViewById(R.id.pays);
        filiere_editText  =  view.findViewById(R.id.filiere);
        departement_editText  =  view.findViewById(R.id.Département);
        etablisement_editText  =  view.findViewById(R.id.établissement);
        tutelle_editText  =  view.findViewById(R.id.Tutelle);

        saveButton = view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                   if(!nom_editText.getText().toString().isEmpty() &&   !prenom_editText.getText().toString().isEmpty() && !CIN_editText.getText().toString().isEmpty() &&   !dateDeNaissance_editText.getText().toString().isEmpty()
                           &&   !addressePostale_editText.getText().toString().isEmpty()  &&   !ville_editText.getText().toString().isEmpty()  &&   !pays_editText.getText().toString().isEmpty()
                           &&   !filiere_editText.getText().toString().isEmpty()  &&   !departement_editText.getText().toString().isEmpty()  &&   !etablisement_editText.getText().toString().isEmpty()
                           &&   !tutelle_editText.getText().toString().isEmpty()
                   ){
                       saveItem(view);
                   }else{
                       Snackbar.make(view, "All Fields Must Be Written!", Snackbar.LENGTH_SHORT).show();
                   }





            }


        });



        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.show(); //very important




    }

    public void saveItem(View view) {


        Item item = new Item();
        item.setNom(nom_editText.getText().toString().trim());
        item.setPrenom((prenom_editText.getText().toString().trim()));
        item.setCIN(CIN_editText.getText().toString().trim());
        item.setDate_de_naissance((dateDeNaissance_editText.getText().toString().trim()));
        item.setAdresse_postale((addressePostale_editText.getText().toString().trim()));
        item.setVille((ville_editText.getText().toString().trim()));
        item.setPays((pays_editText.getText().toString().trim()));
        item.setFiliére((filiere_editText.getText().toString().trim()));
        item.setDépartement((departement_editText.getText().toString().trim()));
        item.setEtablisement((etablisement_editText.getText().toString().trim()));
        item.setTutelle((tutelle_editText.getText().toString().trim()));


        dataBaseHandler.addItem(item);
        Snackbar.make(view, "Item Added Successfuly!", Snackbar.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //code to be run:
                alertDialog.dismiss();

                startActivity(new Intent(List_Activity.this, List_Activity.class));
            }
        }, 1000);






    }








}