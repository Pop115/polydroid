package si3.polytech.polydroid;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Kienan on 09/04/2018.
 */

class NewsViewHolder extends RecyclerView.ViewHolder {

    ImageView image;
    TextView titre;
    TextView categorie;
    TextView date;

    public NewsViewHolder(View itemView, ImageView image, TextView titre, TextView categorie, TextView date) {
        super(itemView);
        this.image = image;
        this.titre = titre;
        this.categorie = categorie;
        this.date = date;
    }

    public ImageView getImage() {
        return image;
    }

    public TextView getTitre() {
        return titre;
    }

    public TextView getCategorie() {
        return categorie;
    }

    public TextView getDate() {
        return date;
    }
}
