package si3.polytech.polydroid;

/**
 * Created by Kienan on 09/04/2018.
 */

public class Localisation {

    String batiment;
    String salle;
    String details;

    public Localisation(){
        this.batiment = "Aucun";
        this.salle = "Aucun";
        this.details = "Aucun";
    }

    public Localisation(String batiment, String salle, String details) {
        this.batiment = batiment;
        this.salle = salle;
        this.details = details;
    }

    @Override
    public String toString() {
        return "Batiment: "+batiment+"\n"+
                "Salle: "+salle+"\n"+
                "Details: "+details;
    }

    public String getBatiment() {
        return batiment;
    }

    public String getSalle() {
        return salle;
    }

    public String getDetails() {
        return details;
    }
}
