package si3.polytech.polydroid;

import android.Manifest;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import si3.polytech.polydroid.models.Contact;
import si3.polytech.polydroid.models.Importance;
import si3.polytech.polydroid.models.Incident;
import si3.polytech.polydroid.models.Localisation;
import si3.polytech.polydroid.models.Type;

/**
 * Created by Kienan on 14/05/2018.
 */

public class FormulaireActivity extends AppCompatActivity {

    Contact contact = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulaire_declaration);

        TextView dateText = (TextView) this.findViewById(R.id.formDate);
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        DecimalFormat mFormat = new DecimalFormat("00");
        dateText.setText(mFormat.format(day) + "/" + mFormat.format(month) + "/" + year);

        TextView dateTextWanted = (TextView) this.findViewById(R.id.formDate2);
        calendar.add(Calendar.DATE, 1);
        int day2 = calendar.get(Calendar.DAY_OF_MONTH);
        int month2 = calendar.get(Calendar.MONTH) + 1;
        int year2 = calendar.get(Calendar.YEAR);
        dateTextWanted.setText(mFormat.format(day2) + "/" + mFormat.format(month2) + "/" + year2);

        ImageButton calendarImage = (ImageButton) this.findViewById(R.id.calendarImage);
        calendarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = new SelectDateDialogFragment((TextView) findViewById(R.id.formDate));
                dialogFragment.show(getFragmentManager(), "DatePicker");
            }
        });

        ImageButton calendarImage2 = (ImageButton) this.findViewById(R.id.calendarImage2);
        calendarImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = new SelectDateDialogFragment((TextView) findViewById(R.id.formDate2));
                dialogFragment.show(getFragmentManager(), "DatePicker");
            }
        });

        Spinner typeSpinner = (Spinner) this.findViewById(R.id.formType);
        List<String> typeList = new ArrayList<String>();
        for (Type type : Type.values()) {
            typeList.add(type.toString());
        }
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, typeList);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);


        Spinner importanceSpinner = (Spinner) this.findViewById(R.id.formImportance);
        List<String> importanceList = new ArrayList<String>();
        for (Importance importance : Importance.values()) {
            importanceList.add(importance.toString());
        }
        ArrayAdapter<String> importanceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, importanceList);
        importanceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        importanceSpinner.setAdapter(importanceAdapter);


        Button addImageButton = (Button) this.findViewById(R.id.add_photo);
        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 1888);
            }
        });

    }

    public void pickContact(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
            startActivityForResult(intent, 1);
        }else{
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    2);

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "Send").setIcon(R.drawable.ic_menu_send)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (sendFormulaire()) {
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                    return true;
                } else {
                    Toast.makeText(getBaseContext(), "Erreur d'envoi", Toast.LENGTH_LONG);
                    return false;
                }
            }
        });
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1888) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ((ImageView) this.findViewById(R.id.image)).setImageBitmap(photo);
        } else if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                contact = new Contact();
                Uri contactData = data.getData();
                Cursor cursor = getContentResolver().query(contactData, null, null, null, null);
                if (cursor.moveToFirst()) {
                    String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    String hasPhone = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                    if (hasPhone.equalsIgnoreCase("1")) {
                        Cursor phones = getContentResolver().query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                                null, null);

                        while (phones.moveToNext())
                            contact.getPhoneNumbers().add("" + phones.getString(phones.getColumnIndex("data1")));

                        phones.moveToFirst();
                        String cNumber = phones.getString(phones.getColumnIndex("data1"));

                        String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        contact.setName(name);

                        TextView formContact = (TextView)findViewById(R.id.formContact);
                        formContact.setText(contact.getPhoneNumbers().get(0));
                        return;
                    }
                }
                Toast.makeText(this, "Contact invalide", Toast.LENGTH_LONG);


            }
        }
    }

    public Incident getIncidentFromFormulaire() {
        EditText titreView = (EditText) findViewById(R.id.formTitre);
        EditText descriptionView = (EditText) findViewById(R.id.formDescription);
        TextView dateView = (TextView) findViewById(R.id.formDate);
        TextView dateView2 = (TextView) findViewById(R.id.formDate2);
        Spinner typeSpinner = (Spinner) findViewById(R.id.formType);
        Spinner importanceSpinner = (Spinner) findViewById(R.id.formImportance);

        Date date = new Date();
        Date dateWanted = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = format.parse(dateView.getText().toString());
            dateWanted = format.parse(dateView2.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Incident newIncident = new Incident(date.getTime(), dateWanted.getTime(), "TestAuteur", new Localisation("batiment", "salle", "details"), descriptionView.getText().toString(), titreView.getText().toString(), Importance.values()[importanceSpinner.getSelectedItemPosition()], Type.values()[typeSpinner.getSelectedItemPosition()]);
        newIncident.setContact(contact);

        return newIncident;
    }

    public boolean sendFormulaire() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Incidents");
        myRef.push().setValue(getIncidentFromFormulaire());

        return true;
    }

}
