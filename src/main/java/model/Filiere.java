package model;

public class Filiere {
    private int id;
    private String libelle;

    public Filiere(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public Filiere() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
