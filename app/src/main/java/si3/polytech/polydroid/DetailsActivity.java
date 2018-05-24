package si3.polytech.polydroid;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Date;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_details);
        Bundle extra = getIntent().getExtras();

        String titre = extra.getString("titre");
        String description = extra.getString("description");
        long timestamp = extra.getLong("timestamp");
        long timestampWanted = extra.getLong("timestampWanted");
        Date date = new Date(timestamp);
        Date dateWanted = new Date(timestampWanted);
        String auteur = extra.getString("auteur");
        Importance importance = (Importance)extra.get("importance");
        Type type = (Type)extra.get("type");

        TextView titreView = (TextView)findViewById(R.id.detailsTitre);
        TextView descriptionView = (TextView)findViewById(R.id.detailsDescription);
        TextView dateView = (TextView)findViewById(R.id.detailsDate);
        TextView dateViewWanted = (TextView)findViewById(R.id.detailsDateWanted);
        TextView importanceView = (TextView)findViewById(R.id.detailsImportance);
        TextView typeView = (TextView)findViewById(R.id.detailsType);
        TextView auteurView = (TextView)findViewById(R.id.detailsAuteur);

        titreView.setText(titre);
        descriptionView.setText(description);
        importanceView.setText(importance.toString());
        typeView.setText(type.toString());
        DecimalFormat mFormat = new DecimalFormat("00");
        dateView.setText(mFormat.format(Double.valueOf(date.getDay())) + "/" + mFormat.format(Double.valueOf(date.getMonth())) + "/" + (date.getYear()+1900));
        dateViewWanted.setText(mFormat.format(Double.valueOf(dateWanted.getDay())) + "/" + mFormat.format(Double.valueOf(dateWanted.getMonth())) + "/" + (dateWanted.getYear()+1900));
        auteurView.setText(auteur);

        final Incident incident = new Incident(timestamp, timestampWanted, auteur, null, description, titre, importance, type);

        ImageButton imageButton = (ImageButton)findViewById(R.id.calendarAddEvent);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCalendarEvent(incident);
            }
        });
    }


    public void addCalendarEvent(Incident incident){
        Intent calIntent = new Intent(Intent.ACTION_INSERT);
        calIntent.setType("vnd.android.cursor.item/event");
        calIntent.putExtra(CalendarContract.Events.TITLE, incident.getTitre());
        calIntent.putExtra(CalendarContract.Events.DESCRIPTION, incident.getDescription());

        calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
        calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                incident.getTimestampWanted());


        startActivity(calIntent);
    }





}
