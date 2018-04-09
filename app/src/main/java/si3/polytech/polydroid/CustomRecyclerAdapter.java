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

public class CustomRecyclerAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    ArrayList<News> newsArrayList = new ArrayList<>();

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext()
                .getSystemService(Context. LAYOUT_INFLATER_SERVICE );
        View convertView = inflater.inflate(R.layout. news_grid_item , null );

        ImageView image = (ImageView)convertView.findViewById(R.id.apercu);
        TextView titre = (TextView)convertView.findViewById(R.id.titre);
        TextView date = (TextView)convertView.findViewById(R.id.date);
        TextView categorie = (TextView)convertView.findViewById(R.id.categorie);

        return new NewsViewHolder(convertView, image, titre, categorie, date);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        News news = newsArrayList.get(position);
        holder.getTitre().setText(news.getTitre());
        holder.getDate().setText(news.getDate());
        holder.getCategorie().setText(news.getCategorie());

        ImageView imageView = holder.getImage();
        AsyncImage asyncImage = new AsyncImage(imageView);
        asyncImage.execute(news.getUrlMedia());


    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }
}
