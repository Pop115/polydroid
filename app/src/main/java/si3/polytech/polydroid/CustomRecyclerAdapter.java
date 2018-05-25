package si3.polytech.polydroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import si3.polytech.polydroid.models.Incident;


/**
 * Created by Kienan on 09/04/2018.
 */

public class CustomRecyclerAdapter extends RecyclerView.Adapter<IncidentViewHolder> {

    ArrayList<Incident> incidentArrayList = new ArrayList<>();

    @Override
    public IncidentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View convertView = inflater.inflate(R.layout.item_incident, null);


        ImageView image = (ImageView) convertView.findViewById(R.id.miniature);
        TextView titre = (TextView) convertView.findViewById(R.id.titre);
        TextView type = (TextView) convertView.findViewById(R.id.type);
        TextView date = (TextView) convertView.findViewById(R.id.date);
        TextView importance = (TextView) convertView.findViewById(R.id.importance);
        TextView auteur = (TextView) convertView.findViewById(R.id.auteur);

        convertView.setOnClickListener(mOnClickListener);

        return new IncidentViewHolder(convertView, image, auteur, titre, type, importance, date);
    }

    @Override
    public void onBindViewHolder(IncidentViewHolder holder, int position) {
        Incident incident = incidentArrayList.get(position);

        DecimalFormat mFormat = new DecimalFormat("00");
        holder.getDate().setText(mFormat.format(Double.valueOf(incident.getDate().getDay())) + "/" + mFormat.format(Double.valueOf(incident.getDate().getMonth())) + "/" + (incident.getDate().getYear()+1900));

        holder.getTitre().setText(incident.getTitre());
        holder.getImportance().setText(incident.getImportance().toString());
        holder.getAuteur().setText(incident.getAuteur());
        holder.getType().setText(incident.getType().toString());

    }

    @Override
    public int getItemCount() {
        return incidentArrayList.size();
    }

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.item_incident).getParent();
            int itemPosition = recyclerView.getChildLayoutPosition(view);

            Incident incident = incidentArrayList.get(itemPosition);
            Intent intent = new Intent(view.getContext(), DetailsActivity.class);

            intent.putExtra("incident", incident);

            view.getContext().startActivity(intent);
        }
    };




}
