package si3.polytech.polydroid.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;


/**
 * Created by Kienan on 09/04/2018.
 */

public class Incident implements Parcelable{

    private String firebaseId;
    private long timestamp;
    private long timestampWanted;
    private String auteur;
    private Localisation localisation;
    private String description;
    private String titre;
    private Importance importance;
    private Type type;
    private Contact contact;

    public Incident(){
        this.timestamp = System.currentTimeMillis();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        this.timestampWanted = cal.getTimeInMillis();
        this.auteur = "Aucun";
        this.localisation = new Localisation("Aucun", "Aucun", "Aucun");
        this.description = "Aucun";
        this.titre = "Aucun";
        this.importance = Importance.Faible;
        this.type = Type.AUTRE;
    }

    public Incident(long timestamp, long timestampWanted, String auteur, Localisation localisation, String description, String titre, Importance importance, Type type) {
        this.timestamp = timestamp;
        this.timestampWanted = timestampWanted;
        this.auteur = auteur;
        this.localisation = localisation;
        this.description = description;
        this.titre = titre;
        this.importance = importance;
        this.type = type;
    }

    protected Incident(Parcel in) {
        timestamp = in.readLong();
        timestampWanted = in.readLong();
        auteur = in.readString();
        localisation = in.readParcelable(Localisation.class.getClassLoader());
        description = in.readString();
        titre = in.readString();
        contact = in.readParcelable(Contact.class.getClassLoader());
        importance = Importance.valueOf(in.readString());
        type = Type.valueOf(in.readString());
        firebaseId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(timestamp);
        dest.writeLong(timestampWanted);
        dest.writeString(auteur);
        dest.writeParcelable(localisation, flags);
        dest.writeString(description);
        dest.writeString(titre);
        dest.writeParcelable(contact, flags);

        dest.writeString(importance.name());
        dest.writeString(type.name());
        dest.writeString(firebaseId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Incident> CREATOR = new Creator<Incident>() {
        @Override
        public Incident createFromParcel(Parcel in) {
            return new Incident(in);
        }

        @Override
        public Incident[] newArray(int size) {
            return new Incident[size];
        }
    };

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("auteur", auteur);
            jsonObject.put("timestamp", timestamp);
            jsonObject.put("localisation", localisation.toString());
            jsonObject.put("description", description);
            jsonObject.put("titre", titre);
            jsonObject.put("importance", importance.toString());
            jsonObject.put("type", type.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public String getFirebaseId() {
        return firebaseId;
    }

    public void setFirebaseId(String firebaseId) {
        this.firebaseId = firebaseId;
    }

    public void setContact(Contact contact){
        this.contact = contact;
    }

    public Contact getContact() {
        return contact;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public long getTimestampWanted(){return timestampWanted;}

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Date getDate(){
        return new Date(timestamp);
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
