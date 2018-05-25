package si3.polytech.polydroid.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Contact implements Parcelable{

    private String name;
    private ArrayList<String> phoneNumbers;  // a list for storing multiple numbers
    private ArrayList<String> emails;

    public Contact(){
        phoneNumbers = new ArrayList<>();
        emails = new ArrayList<>();
    }

    public Contact(String name, String prenom, ArrayList<String> phoneNumbers, ArrayList<String> emails) {
        this.name = name;
        this.phoneNumbers = phoneNumbers;
        this.emails = emails;
    }

    protected Contact(Parcel in) {
        name = in.readString();
        phoneNumbers = in.createStringArrayList();
        emails = in.createStringArrayList();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeStringList(phoneNumbers);
        dest.writeStringList(emails);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(ArrayList<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public ArrayList<String> getEmails() {
        return emails;
    }

    public void setEmails(ArrayList<String> emails) {
        this.emails = emails;
    }
}
