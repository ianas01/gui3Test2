package gui3.example.gui3.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import gui3.example.gui3.Constants;
import gui3.example.gui3.item.Item;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {

    private final Context context;

    public DataBaseHandler(@Nullable Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BABY_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + "("
                + Constants.KEY_ID + " INTEGER PRIMARY KEY,"
                + Constants.KEY_NOM+ " TEXT,"
                + Constants.KEY_PRENOM + " TEXT,"
                + Constants.KEY_CIN + " TEXT,"
                + Constants.KEY_Date_De_Naissance + " TEXT,"
                + Constants.KEY_Adresse_postale + " TEXT,"
                + Constants.KEY_ville + " TEXT,"
                + Constants.KEY_pays + " TEXT,"
                + Constants.KEY_filiére + " TEXT,"
                + Constants.KEY_Departement + " TEXT,"
                + Constants.KEY_Établissement + " TEXT,"
                + Constants.KEY_Tutelle + " TEXT,"
                + Constants.KEY_Date_Ajouté + " LONG);";

        db.execSQL(CREATE_BABY_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);

        onCreate(db);
    }

    // Les Operations CRUD:


    //ajouter un element
    public void addItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.KEY_NOM, item.getNom());
        values.put(Constants.KEY_PRENOM, item.getPrenom());
        values.put(Constants.KEY_CIN, item.getCIN());
        values.put(Constants.KEY_Date_De_Naissance, item.getDate_de_naissance());
        values.put(Constants.KEY_Adresse_postale, item.getAdresse_postale());
        values.put(Constants.KEY_ville, item.getVille());
        values.put(Constants.KEY_pays, item.getPays());
        values.put(Constants.KEY_filiére, item.getFiliére());
        values.put(Constants.KEY_Departement, item.getDépartement());
        values.put(Constants.KEY_Établissement, item.getEtablisement());
        values.put(Constants.KEY_Tutelle, item.getTutelle());
        values.put(Constants.KEY_Date_Ajouté ,java.lang.System.currentTimeMillis());//timestamp de systeme

        //Inserer dans les colones
        db.insert(Constants.TABLE_NAME, null, values);


    }

    //Obtenir un element
    public Item getItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Constants.TABLE_NAME,
                new String[]{Constants.KEY_ID,
                        Constants.KEY_NOM,
                        Constants.KEY_PRENOM,
                        Constants.KEY_CIN,
                        Constants.KEY_Date_De_Naissance,
                        Constants.KEY_Adresse_postale,
                        Constants.KEY_ville,
                        Constants.KEY_pays,
                        Constants.KEY_filiére,
                        Constants.KEY_Departement,
                        Constants.KEY_Établissement,
                        Constants.KEY_Tutelle,
                        Constants.KEY_Date_Ajouté,

                },
                Constants.KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Item item = new Item();
        if (cursor != null) {
            item.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
            item.setNom(cursor.getString(cursor.getColumnIndex(Constants.KEY_NOM)));
            item.setPrenom(cursor.getString(cursor.getColumnIndex(Constants.KEY_PRENOM)));
            item.setDate_de_naissance(cursor.getString(cursor.getColumnIndex(Constants.KEY_Date_De_Naissance)));
            item.setAdresse_postale(cursor.getString(cursor.getColumnIndex(Constants.KEY_Adresse_postale)));
            item.setVille(cursor.getString(cursor.getColumnIndex(Constants.KEY_ville)));
            item.setPays(cursor.getString(cursor.getColumnIndex(Constants.KEY_pays)));
            item.setFiliére(cursor.getString(cursor.getColumnIndex(Constants.KEY_filiére)));
            item.setDépartement(cursor.getString(cursor.getColumnIndex(Constants.KEY_Departement)));
            item.setEtablisement(cursor.getString(cursor.getColumnIndex(Constants.KEY_Établissement)));
            item.setTutelle(cursor.getString(cursor.getColumnIndex(Constants.KEY_Tutelle)));


            //convert Timestamp to something readable
            //converter Timestamp en quelque chose de lisible
            DateFormat dateFormat = DateFormat.getDateInstance();
            String formattedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_Date_Ajouté)))
                    .getTime()); // Feb 23, 2020

            item.setDate_ajouté(formattedDate);


        }

        return item;
    }

    //Get all Items
    public List<Item> getAllItems() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Item> itemList = new ArrayList<>();

        Cursor cursor = db.query(Constants.TABLE_NAME,
                new String[]{Constants.KEY_ID,
                        Constants.KEY_NOM,
                        Constants.KEY_PRENOM,
                        Constants.KEY_CIN,
                        Constants.KEY_Date_De_Naissance,
                        Constants.KEY_Adresse_postale,
                        Constants.KEY_ville,
                        Constants.KEY_pays,
                        Constants.KEY_filiére,
                        Constants.KEY_Departement,
                        Constants.KEY_Établissement,
                        Constants.KEY_Tutelle,
                        Constants.KEY_Date_Ajouté},
                        null, null, null, null,
                         " DESC");

        //Item item;
        if (cursor.moveToFirst()) {
            do {
                Item item = new Item();
                item.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
                item.setNom(cursor.getString(cursor.getColumnIndex(Constants.KEY_NOM)));
                item.setPrenom(cursor.getString(cursor.getColumnIndex(Constants.KEY_PRENOM)));
                item.setCIN(cursor.getString(cursor.getColumnIndex(Constants.KEY_CIN)));
                item.setDate_de_naissance(cursor.getString(cursor.getColumnIndex(Constants.KEY_Date_De_Naissance)));
                item.setAdresse_postale(cursor.getString(cursor.getColumnIndex(Constants.KEY_Adresse_postale)));
                item.setVille(cursor.getString(cursor.getColumnIndex(Constants.KEY_ville)));
                item.setPays(cursor.getString(cursor.getColumnIndex(Constants.KEY_pays)));
                item.setFiliére(cursor.getString(cursor.getColumnIndex(Constants.KEY_filiére)));
                item.setDépartement(cursor.getString(cursor.getColumnIndex(Constants.KEY_Departement)));
                item.setEtablisement(cursor.getString(cursor.getColumnIndex(Constants.KEY_Établissement)));
                item.setTutelle(cursor.getString(cursor.getColumnIndex(Constants.KEY_Tutelle)));


                //convert Timestamp to something readable
                DateFormat dateFormat = DateFormat.getDateInstance();
                String formattedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_Date_Ajouté)))
                        .getTime()); // Feb 23, 2020
                item.setDate_ajouté(formattedDate);

                //Add to arraylist
                itemList.add(item);
            } while (cursor.moveToNext());
        }
        return itemList;

    }


    public int updateItem(Item item){
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(Constants.KEY_NOM, item.getNom());
            values.put(Constants.KEY_PRENOM, item.getPrenom());
            values.put(Constants.KEY_CIN, item.getCIN());
            values.put(Constants.KEY_Date_De_Naissance, item.getDate_de_naissance());
            values.put(Constants.KEY_Adresse_postale, item.getAdresse_postale());
            values.put(Constants.KEY_ville, item.getVille());
            values.put(Constants.KEY_pays, item.getPays());
            values.put(Constants.KEY_filiére, item.getFiliére());
            values.put(Constants.KEY_Departement, item.getDépartement());
            values.put(Constants.KEY_Établissement, item.getEtablisement());
            values.put(Constants.KEY_Tutelle, item.getTutelle());
            values.put(Constants.KEY_Date_Ajouté, java.lang.System.currentTimeMillis());//timestamp de systeme

            //update row
            return db.update(Constants.TABLE_NAME, values,
                    Constants.KEY_ID + "=?",
                    new String[]{String.valueOf(item.getId())});

        }

        //Todo: Add Delete Item
        public void deleteItem ( int id){
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(Constants.TABLE_NAME,
                    Constants.KEY_ID + "=?",
                    new String[]{String.valueOf(id)});

            //close
            db.close();

        }

        //Todo: getItemCount
        public int getItemsCount () {
            String countQuery = "SELECT * FROM " + Constants.TABLE_NAME;
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.rawQuery(countQuery, null);

            return cursor.getCount();

        }
















    /*private final Context context;

    public DataBaseHandler(@Nullable Context context) {
        super(context, Util.databaseName, null, Util.dataBaseVersion);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //myTable ( id, babyItem, quantity, color, size)
        //Creating our table!

        String comm1 = "CREATE TABLE " +Util.tableName + "(" +Util.id + " INTEGER PRIMARY KEY," +Util.itemName + " INTEGER,"+ Util.color + " TEXT," +Util.quantity + " INTEGER);";

        sqLiteDatabase.execSQL(comm1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            String comm2 = "DROP TABLE IF EXISTS " + Util.tableName;
            sqLiteDatabase.execSQL(comm2);
            onCreate(sqLiteDatabase);
    }
    //crud
    //TODO: create an ADD ITEM!

    public void addItem(Item item){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Util.itemName, item.getItemName());
        values.put(Util.color, item.getColor());
        values.put(Util.quantity, item.getQuantity());
       // values.put(Util.size, item.getSize());
        //values.put(Util.timeUpdated, java.lang.System.currentTimeMillis()  );

        db.insert(Util.tableName, null, values);


    }

    public Item getItem(int id){
         SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Util.tableName, new String[]{Util.id, Util.itemName,  Util.color,Util.quantity}, Util.id + "=?",new String[]{String.valueOf(id)}, null, null, null );
        if(cursor != null){
            cursor.moveToFirst();
        }
        Item item = new Item();
        item.setItemName(cursor.getString(0));
        //item.setQuantity(Integer.parseInt(cursor.getString(1)));
        item.setColor(cursor.getString(2));
        item.setQuantity(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Util.quantity))));

       // item.setSize(Integer.parseInt(cursor.getString(3)));


        //TODO:how to convert time millis to a readeble number:
        DateFormat dateFormat = DateFormat.getDateInstance();
        String dateForm = dateFormat.format(new Date(cursor.getString(4)).getTime());
       // item.setDateTimeAdded(dateForm);


        return item;


    }

    public ArrayList<Item> getAllItems(){

        ArrayList<Item> arrayList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String sqlCommand = " SELECT * FROM BABYNEEDS ORDER BY DESQ ";
        //Cursor cursor = db.rawQuery(sqlCommand, null );
        Cursor cursor = db.query(Util.tableName, new String[]{Util.id, Util.itemName,  Util.color,Util.quantity}, null,null, null, null, Util.timeUpdated + " DESC" );
        if(cursor.moveToFirst()){
            do{
                 Item item = new Item();
                 item.setId(cursor.getInt(cursor.getColumnIndex(Util.id)));
                item.setItemName(cursor.getString(cursor.getColumnIndex(Util.itemName)));
                item.setColor(cursor.getString(cursor.getColumnIndex(Util.color)));
                item.setQuantity(cursor.getInt(cursor.getColumnIndex(Util.quantity)));
               // item.setQuantity(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Util.quantity))));

               // item.setSize(cursor.getInt(cursor.getColumnIndex(Util.size)));

                DateFormat dateFormat = DateFormat.getDateInstance();
                String dateForm = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Util.timeUpdated))));
               // item.setDateTimeAdded(dateForm);

                arrayList.add(item);

            }while (cursor.moveToNext());

        }

        return arrayList;


    }



    public int replaceItem(Item item){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.itemName, item.getItemName() );
        contentValues.put(Util.quantity, item.getQuantity() );
        contentValues.put(Util.color, item.getColor() );
       // contentValues.put(Util.size, item.getSize() );

       // contentValues.put(Util.timeUpdated, java.lang.System.currentTimeMillis() );
        return db.update(Util.tableName, contentValues, Util.id + "=?", new String[]{String.valueOf(item.getId())});
    }

    public void deleteItem(int id){

        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(Util.tableName, Util.id + "=?", new String[]{String.valueOf(id)});
        database.close();
    }

    public int getItemCount(){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM "+ Util.tableName, null);
        return cursor.getCount();

    }
*/

    }
