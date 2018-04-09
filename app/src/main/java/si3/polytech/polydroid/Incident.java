package si3.polytech.polydroid;

import java.util.Date;

/**
 * Created by Kienan on 09/04/2018.
 */

public class Incident {

    private Date date;
    private String auteur;
    private Localisation localisation;
    private String description;
    private String titre;
    private Importance importance;
    private Type type;

    public Incident(Date date, String auteur, Localisation localisation, String description, String titre, Importance importance, Type type) {
        this.date = date;
        this.auteur = auteur;
        this.localisation = localisation;
        this.description = description;
        this.titre = titre;
        this.importance = importance;
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public Localisation getLocalisation() {
        return localisation;
    }

    public void setLocalisation(Localisation localisation) {
        this.localisation = localisation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Importance getImportance() {
        return importance;
    }

    public void setImportance(Importance importance) {
        this.importance = importance;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
