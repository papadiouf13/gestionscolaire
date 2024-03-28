package model;

public class Classe {
    private int id;
    private String libelle;
    private String code;
    private int frais_inscription;
    private int mensualite;
    private int autres_frais;
    private Filiere filiere;

    public Classe(int id, String libelle, String code, int frais_inscription, int mensualite, int autres_frais) {
        this.id = id;
        this.libelle = libelle;
        this.code = code;
        this.frais_inscription = frais_inscription;
        this.mensualite = mensualite;
        this.autres_frais = autres_frais;
    }

    public Classe() {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getFrais_inscription() {
        return frais_inscription;
    }

    public void setFrais_inscription(int frais_inscription) {
        this.frais_inscription = frais_inscription;
    }

    public int getMensualite() {
        return mensualite;
    }

    public void setMensualite(int mensualite) {
        this.mensualite = mensualite;
    }

    public int getAutres_frais() {
        return autres_frais;
    }

    public void setAutres_frais(int autres_frais) {
        this.autres_frais = autres_frais;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                " / libelle='" + libelle +
                " / code='" + code +
                " / frais_inscription=" + frais_inscription +
                " / mensualite=" + mensualite +
                " / autres_frais=" + autres_frais +
                " / filiere_id=" + filiere.getId() +
                " / filiere_libelle=" + filiere.getLibelle();

    }
}
