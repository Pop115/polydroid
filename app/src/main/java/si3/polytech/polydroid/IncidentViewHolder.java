package si3.polytech.polydroid;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Kienan on 09/04/2018.
 */

class IncidentViewHolder extends RecyclerView.ViewHolder {

    TextView auteur;
    TextView type;
    ImageView image;
    TextView titre;
    TextView importance;
    TextView date;

    public IncidentViewHolder(View itemView, ImageView image, TextView auteur, TextView titre, TextView type, TextView importance, TextView date) {
        super(itemView);
        this.auteur = auteur;
        this.type = type;
        this.image = image;
        this.titre = titre;
        this.importance = importance;
        this.date = date;
    }


    public TextView getType() {
        return type;
    }

    public TextView getAuteur() {
        return auteur;
    }

    public ImageView getImage() {
        return image;
    }

    public TextView getTitre() {
        return titre;
    }

    public TextView getImportance() {
        return importance;
    }

    public TextView getDate() {
        return date;
    }
}
