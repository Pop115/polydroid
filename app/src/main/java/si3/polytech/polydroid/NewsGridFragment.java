package si3.polytech.polydroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Kienan on 25/03/2018.
 */

public class NewsGridFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    ArrayList<News> articleList = new ArrayList<>();
    NewsDBHelper newsDBHelper;

    public NewsGridFragment() {

    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static NewsGridFragment newInstance() {
        NewsGridFragment fragment = new NewsGridFragment();
        Bundle args = new Bundle();
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news_grid, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);

        try {
            newsDBHelper = new NewsDBHelper(getContext());
            newsDBHelper.createDataBase();
            newsDBHelper.openDataBase();
            newsDBHelper.getAllArticles();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        articleList.add(new News(1, "unLivre", "pour les nuls", "nulsAuteur", "26/03/1997", "SOCIETE", "IMAGE", "http://static.eyrolles.com/img/2/7/5/4/0/3/1/7/9782754031790_h430.jpg"));
        articleList.add(new News(2, "rickRoll", "never gonna give you up", "rick astley", "12/12/12", "POLITIQUE", "VIDEO", "https://img.youtube.com/vi/dQw4w9WgXcQ/default.jpg"));

        articleList.addAll(newsDBHelper.getAllArticles());

        RecyclerView recyclerView = (RecyclerView) this.getView().findViewById(R.id.grid);
        CustomRecyclerAdapter adapter = new CustomRecyclerAdapter();
        adapter.newsArrayList.addAll(articleList);
        recyclerView.setAdapter(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);


    }
}
