package si3.polytech.polydroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
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

public class IncidentFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    ArrayList<Incident> incidentArrayList = new ArrayList<>();
    IncidentDBHelper newsDBHelper;

    public IncidentFragment() {

    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static IncidentFragment newInstance() {
        IncidentFragment fragment = new IncidentFragment();
        Bundle args = new Bundle();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_recycler, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);

        try {
            newsDBHelper = new IncidentDBHelper(getContext());
            newsDBHelper.createDataBase();
            newsDBHelper.openDataBase();
            newsDBHelper.getAllArticles();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        incidentArrayList.addAll(newsDBHelper.getAllArticles());

        //NewsCustomAdapter arrayAdapter = new NewsCustomAdapter(this.getContext(), android.R.layout.simple_list_item_1, articleList);
        //GridView gridView = (GridView) this.getView().findViewById(R.id.grid);
        //gridView.setAdapter(arrayAdapter);

        RecyclerView recyclerView = (RecyclerView) this.getView().findViewById(R.id.recycler);
        CustomRecyclerAdapter adapter = new CustomRecyclerAdapter();
        adapter.incidentArrayList.addAll(incidentArrayList);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

    }

}
