package si3.polytech.polydroid.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Kienan on 09/04/2018.
 */

public class Localisation implements Parcelable{

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

    protected Localisation(Parcel in) {
        batiment = in.readString();
        salle = in.readString();
        details = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(batiment);
        dest.writeString(salle);
        dest.writeString(details);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Localisation> CREATOR = new Creator<Localisation>() {
        @Override
        public Localisation createFromParcel(Parcel in) {
            return new Localisation(in);
        }

        @Override
        public Localisation[] newArray(int size) {
            return new Localisation[size];
        }
    };

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
