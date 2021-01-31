package gui3.example.gui3.item;

public class Item {

    private int id;
    private String Nom;
    private String Prenom;
    private String CIN;
    private String Date_de_naissance;
    private String Adresse_postale;
    private String Ville;
    private String Pays;
    private String Filiére;
    private String Département;
    private String Etablisement;
    private String Tutelle;
    private String Date_ajouté;

    // vide constructor

    public Item() {
    }

    public Item(int id, String nom, String prenom, String CIN, String date_de_naissance,
                String adresse_postale, String ville, String pays,
                String filiére, String département, String etablisement,
                String tutelle, String date_ajouté)
    {
        this.id = id;
        Nom = nom;
        Prenom = prenom;
        this.CIN = CIN;
        Date_de_naissance = date_de_naissance;
        Adresse_postale = adresse_postale;
        Ville = ville;
        Pays = pays;
        Filiére = filiére;
        Département = département;
        Etablisement = etablisement;
        Tutelle = tutelle;
        Date_ajouté = date_ajouté;
    }

    //Getters and Setters methods:


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public String getDate_de_naissance() {
        return Date_de_naissance;
    }

    public void setDate_de_naissance(String date_de_naissance) {
        Date_de_naissance = date_de_naissance;
    }

    public String getAdresse_postale() {
        return Adresse_postale;
    }

    public void setAdresse_postale(String adresse_postale) {
        Adresse_postale = adresse_postale;
    }

    public String getVille() {
        return Ville;
    }

    public void setVille(String ville) {
        Ville = ville;
    }

    public String getPays() {
        return Pays;
    }

    public void setPays(String pays) {
        Pays = pays;
    }

    public String getFiliére() {
        return Filiére;
    }

    public void setFiliére(String filiére) {
        Filiére = filiére;
    }

    public String getDépartement() {
        return Département;
    }

    public void setDépartement(String département) {
        Département = département;
    }

    public String getEtablisement() {
        return Etablisement;
    }

    public void setEtablisement(String etablisement) {
        Etablisement = etablisement;
    }

    public String getTutelle() {
        return Tutelle;
    }

    public void setTutelle(String tutelle) {
        Tutelle = tutelle;
    }

    public String getDate_ajouté() {
        return Date_ajouté;
    }

    public void setDate_ajouté(String date_ajouté) {
        Date_ajouté = date_ajouté;
    }

/*

    public Item(int id, String itemName, int quantity, String color, int size, String dateTimeAdded) {
        this.id = id;
        this.itemName = itemName;
        this.quantity = quantity;
        this.color = color;
        this.size = size;
        DateTimeAdded = dateTimeAdded;
    }

    public Item(int id, String itemName, int quantity, String color, int size) {
        this.id = id;
        this.itemName = itemName;
        this.quantity = quantity;
        this.color = color;
        this.size = size;
    }



    public Item(String itemName, int quantity, String color, int size) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.color = color;
        this.size = size;
    }

    */


}
