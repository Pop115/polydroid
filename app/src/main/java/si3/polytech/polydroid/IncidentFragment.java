package si3.polytech.polydroid;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Kienan on 25/03/2018.
 */

public class IncidentFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public CustomRecyclerAdapter adapter = new CustomRecyclerAdapter();

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

        RecyclerView recyclerView = (RecyclerView) this.getView().findViewById(R.id.recycler);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getView().getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        final FirebaseDatabase database =  FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Incidents");
        ref.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Incident incident = dataSnapshot.getValue(Incident.class);
                incident.setFirebaseId(dataSnapshot.getKey());
                adapter.incidentArrayList.add(incident);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Incident incident = dataSnapshot.getValue(Incident.class);
                adapter.incidentArrayList.remove(incident);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                adapter.notifyDataSetChanged();

            }
        });



    }


}
