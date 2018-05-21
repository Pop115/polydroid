package si3.polytech.polydroid;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Kienan on 14/05/2018.
 */

public class FormulaireActivity extends AppCompatActivity {

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
        dateText.setText(mFormat.format(Double.valueOf(day)) + "/" + mFormat.format(Double.valueOf(month)) + "/" + year);

        ImageView calendarImage = (ImageView) this.findViewById(R.id.calendarImage);
        calendarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = new SelectDateDialogFragment();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "Send").setIcon(R.drawable.ic_menu_send)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return sendFormulaire();
            }
        });
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1888) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ((ImageView) this.findViewById(R.id.image)).setImageBitmap(photo);
        }
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int year, int month, int day) {

        }
    };

    public boolean sendFormulaire(){
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Incidents");

        EditText titreView = (EditText)findViewById(R.id.formTitre);
        EditText descriptionView = (EditText)findViewById(R.id.formDescription);
        TextView dateView = (TextView)findViewById(R.id.formDate);
        Spinner typeSpinner = (Spinner)findViewById(R.id.formType);
        Spinner importanceSpinner = (Spinner)findViewById(R.id.formImportance);

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = format.parse(dateView.getText().toString());
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Incident newIncident = new Incident(date.getTime(), "TestAuteur", new Localisation("batiment", "salle", "details"), descriptionView.getText().toString(), titreView.getText().toString(), Importance.values()[importanceSpinner.getSelectedItemPosition()], Type.values()[typeSpinner.getSelectedItemPosition()]);
        myRef.push().setValue(newIncident);

        return true;
    }

}
