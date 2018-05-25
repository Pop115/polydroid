package si3.polytech.polydroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.util.Date;

import si3.polytech.polydroid.models.Incident;

public class DetailsActivity extends AppCompatActivity {

    String firebaseid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_details);
        Bundle extra = getIntent().getExtras();

        final Incident incident = extra.getParcelable("incident");
        firebaseid = incident.getFirebaseId();
        Date date = new Date(incident.getTimestamp());
        Date dateWanted = new Date(incident.getTimestampWanted());

        TextView titreView = (TextView) findViewById(R.id.detailsTitre);
        TextView descriptionView = (TextView) findViewById(R.id.detailsDescription);
        TextView dateView = (TextView) findViewById(R.id.detailsDate);
        TextView dateViewWanted = (TextView) findViewById(R.id.detailsDateWanted);
        TextView importanceView = (TextView) findViewById(R.id.detailsImportance);
        TextView typeView = (TextView) findViewById(R.id.detailsType);
        TextView auteurView = (TextView) findViewById(R.id.detailsAuteur);
        TextView contactView = (TextView) findViewById(R.id.detailsContact);
        TextView phoneView = (TextView) findViewById(R.id.detailsContactNumber);

        titreView.setText(incident.getTitre());
        descriptionView.setText(incident.getDescription());
        importanceView.setText(incident.getImportance().toString());
        typeView.setText(incident.getType().toString());
        DecimalFormat mFormat = new DecimalFormat("00");
        dateView.setText(mFormat.format(Double.valueOf(date.getDay())) + "/" + mFormat.format(Double.valueOf(date.getMonth())) + "/" + (date.getYear() + 1900));
        dateViewWanted.setText(mFormat.format(Double.valueOf(dateWanted.getDay())) + "/" + mFormat.format(Double.valueOf(dateWanted.getMonth())) + "/" + (dateWanted.getYear() + 1900));
        auteurView.setText(incident.getAuteur());

        if (incident.getContact() != null && incident.getContact().getName() != null && !incident.getContact().getPhoneNumbers().isEmpty()) {
            contactView.setText(incident.getContact().getName());
            phoneView.setText(incident.getContact().getPhoneNumbers().get(0));
        }else{
            findViewById(R.id.contactLinearLayout).setVisibility(View.GONE);
        }
        ImageButton imageButton = (ImageButton) findViewById(R.id.calendarAddEvent);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCalendarEvent(incident);
            }
        });
    }


    public void addCalendarEvent(Incident incident) {
        Intent calIntent = new Intent(Intent.ACTION_INSERT);
        calIntent.setType("vnd.android.cursor.item/event");
        calIntent.putExtra(CalendarContract.Events.TITLE, incident.getTitre());
        calIntent.putExtra(CalendarContract.Events.DESCRIPTION, incident.getDescription());

        calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
        calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                incident.getTimestampWanted());

        if (calIntent.resolveActivity(getPackageManager()) != null)
            startActivity(calIntent);
    }


    public void callContact(View view) {
        String phoneNumber = ((TextView) findViewById(R.id.detailsContactNumber)).getText().toString();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void messageContact(View view) {
        String phoneNumber = ((TextView) findViewById(R.id.detailsContactNumber)).getText().toString();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void deleteIncident(View view){
        final FirebaseDatabase database =  FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Incidents");

        ref.child(firebaseid).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    Toast.makeText(getBaseContext(), "Incident supprimé", Toast.LENGTH_LONG);
                    getBaseContext().startActivity(intent);
                }else{
                    Toast.makeText(getBaseContext(), "Echec de la suppression", Toast.LENGTH_LONG);

                }
            }
        });


    }


}
