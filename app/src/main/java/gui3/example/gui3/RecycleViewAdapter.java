package gui3.example.gui3;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.babyneeds.R;

import gui3.example.gui3.data.DataBaseHandler;
import gui3.example.gui3.item.Item;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    public Context context;
    public List<Item> itemList;
    private AlertDialog alertDialog;
    private AlertDialog.Builder builder;


    public RecycleViewAdapter(Context context, List<Item> itemList) {
         this.itemList = itemList;
         this.context = context;
    }

    @NonNull
    @Override
    public RecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item2, parent, false);

        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapter.ViewHolder holder, int position) {

            Item item = itemList.get(position);

            holder.nom.setText("Nom:"+item.getNom());
            holder.prenom.setText("Prenom :"+item.getPrenom() +"");
            holder.naissance.setText("Naissance :"+item.getDate_de_naissance()+"");
            holder.cin.setText("CIN :"+item.getCIN());
            holder.postale.setText("Postale :"+item.getAdresse_postale());
            holder.ville.setText("Ville :"+item.getVille());
            holder.pays.setText("Pays :"+item.getPays());
            holder.filiere.setText("Filiere :"+item.getFiliére());
            holder.departement.setText("Depa :"+item.getDépartement());
            holder.etablisement.setText("Eta :"+item.getEtablisement());
            holder.tutelle.setText("Tutelle :"+item.getTutelle());

            holder.date_ajouté.setText("Time Added :"+item.getDate_ajouté());



    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView nom;
        public TextView prenom;
        public TextView naissance;
        public TextView cin;
        public TextView postale;
        public TextView ville;
        public TextView pays;
        public TextView filiere;
        public TextView departement;
        public TextView etablisement;
        public TextView tutelle;
        public TextView date_ajouté;


        public Button editButton;
        public Button deleteButton;
        public int id;

        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            context = context;
            nom = itemView.findViewById(R.id.nom);
            prenom = itemView.findViewById(R.id.prenom);
            cin = itemView.findViewById(R.id.cin);
            naissance = itemView.findViewById(R.id.date_de_naissance);
            postale = itemView.findViewById(R.id.adresse_postale);
            ville = itemView.findViewById(R.id.ville);
            pays = itemView.findViewById(R.id.pays);
            filiere = itemView.findViewById(R.id.filiere);
            departement = itemView.findViewById(R.id.établissement);
            tutelle = itemView.findViewById(R.id.Tutelle);

            date_ajouté = itemView.findViewById(R.id.date_dajout);

            editButton = itemView.findViewById(R.id.modifier);
            deleteButton = itemView.findViewById(R.id.supprimer);

            editButton.setOnClickListener(this);
            deleteButton.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Item item;
            switch (view.getId()){

                case R.id.modifier:
                    //update the item
                    item = itemList.get(position);
                    updateItem(item);
                    break;
                case R.id.supprimer:
                    //delete the item

                    item =  itemList.get(position);
                    deleteItem(item.getId());
                    break;

            }
        }

        private void updateItem(Item newItem) {
            Item item = itemList.get(getAdapterPosition());
            builder = new AlertDialog.Builder(context);
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.popup, null);
            Button saveButton = view.findViewById(R.id.saveButton);
            EditText nom = view.findViewById(R.id.nom);
            EditText prenom = view.findViewById(R.id.prenom);
            EditText cin = view.findViewById(R.id.cin);
            EditText naissance = view.findViewById(R.id.date_de_naissance);
            EditText postale = view.findViewById(R.id.adresse_postale);
            EditText ville = view.findViewById(R.id.ville);
            EditText pays = view.findViewById(R.id.pays);
            EditText filiere = view.findViewById(R.id.filiere);
            EditText departement = view.findViewById(R.id.Département);
            EditText etablissement = view.findViewById(R.id.établissement);
            EditText tutelle = view.findViewById(R.id.Tutelle);

            TextView textView = view.findViewById(R.id.title);


            textView.setText("Editer ");

            nom.setText(item.getNom());
            prenom.setText(item.getPrenom());
            cin.setText(item.getCIN());
            naissance.setText(item.getDate_de_naissance());
            postale.setText(item.getAdresse_postale());
            ville.setText(item.getVille());
            pays.setText(item.getPays());
            filiere.setText(item.getFiliére());
            departement.setText(item.getDépartement());
            etablissement.setText(item.getEtablisement());
            tutelle.setText(item.getTutelle());
            date_ajouté.setText(item.getDate_ajouté());




            builder.setView(view);
            alertDialog = builder.create();
            alertDialog.show();

           saveButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   DataBaseHandler db = new DataBaseHandler(context);

                   newItem.setNom(nom.getText().toString().trim());
                   newItem.setPrenom(prenom.getText().toString().trim());
                   newItem.setCIN(cin.getText().toString().trim());
                   newItem.setDate_de_naissance(naissance.getText().toString().trim());
                   newItem.setAdresse_postale(postale.getText().toString().trim());
                   newItem.setVille(ville.getText().toString().trim());
                   newItem.setPays(pays.getText().toString().trim());
                   newItem.setFiliére(filiere.getText().toString().trim());
                   newItem.setDépartement(departement.getText().toString().trim());
                   newItem.setEtablisement(etablissement.getText().toString().trim());
                   newItem.setTutelle(tutelle.getText().toString().trim());






                   db.updateItem(item);

                   alertDialog.dismiss();
                   notifyItemChanged(getAdapterPosition());
               }
           });


        }


        private void deleteItem(int id) {
            builder = new AlertDialog.Builder(context);
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.confirmation_popup, null);
            builder.setView(view);
            alertDialog = builder.create();
            alertDialog.show();
            Button buttonYes = view.findViewById(R.id.yesButton);
            Button buttonNo = view.findViewById(R.id.noButton);
            buttonYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DataBaseHandler dataBaseHandler = new DataBaseHandler(context);
                    dataBaseHandler.deleteItem(id);
                    itemList.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    alertDialog.dismiss();
                }
            });

            buttonNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                }
            });

        }



    }




}
