package si3.polytech.polydroid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


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
        TextView date = (TextView) convertView.findViewById(R.id.date);
        TextView categorie = (TextView) convertView.findViewById(R.id.categorie);


        return new IncidentViewHolder(convertView, image, titre, categorie, date);
    }

    @Override
    public void onBindViewHolder(IncidentViewHolder holder, int position) {
        Incident incident = incidentArrayList.get(position);

        holder.getTitre().setText(incident.getTitre());
        holder.getDate().setText(incident.getDate().toString());
        holder.getCategorie().setText(incident.getDescription());

        /*
        ImageView imageView = holder.getImage();
        AsyncImage asyncImage = new AsyncImage(imageView);
        asyncImage.execute(news.getUrlMedia());
        */

    }

    @Override
    public int getItemCount() {
        return incidentArrayList.size();
    }
}
