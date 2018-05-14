package si3.polytech.polydroid;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kienan on 25/03/2018.
 */

public class IncidentFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    CustomRecyclerAdapter adapter = new CustomRecyclerAdapter();

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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        AsyncDBHelper incidentDBHelper = new AsyncDBHelper();
        incidentDBHelper.execute();

    }


    private class AsyncDBHelper extends AsyncTask<Void, Void, ArrayList<Incident>> {

        @Override
        protected ArrayList<Incident> doInBackground(Void... voids) {
            try {
                return getAllArticles();
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("-----------Loading-----------");
        }


        @Override
        protected void onPostExecute(ArrayList<Incident> result) {
            super.onPostExecute(result);
            System.out.println(result);
            if (result != null)
                adapter.incidentArrayList.addAll(result);
            adapter.notifyDataSetChanged();
        }

        public ArrayList<Incident> getAllArticles() throws JSONException {
            ArrayList<Incident> incidentArrayList = new ArrayList<>();
            JSONArray incidentJSON = null;
            String stringFromURL = getStringFromURL("http://captainpop.alwaysdata.net/incidents");

            incidentJSON = new JSONArray(stringFromURL);


            for (int i = 0; i < incidentJSON.length(); i++) {
                try {
                    JSONObject jsonObject = incidentJSON.getJSONObject(i);
                    Date date = new Date(jsonObject.getString("date"));
                    String auteur = jsonObject.getString("auteur");
                    String localisation = jsonObject.getString("localisation");
                    String description = jsonObject.getString("description");
                    String titre = jsonObject.getString("titre");
                    String type = jsonObject.getString("type");
                    String importance = jsonObject.getString("importance");
                    String URLImage = jsonObject.getString("URLMiniature");
                    int id = jsonObject.getInt("id");
                    incidentArrayList.add(new Incident(date, auteur, new Localisation("", "", localisation), description, titre, Importance.valueOf(importance), Type.valueOf(type)));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


            Incident newIncident = new Incident(new Date(), "Moi", new Localisation("TEMPLIERS", "E+155", "jolie salle"), "Chaise cassée, gros problème!", "Chaise cassée", Importance.Critique, Type.ELEC);
            incidentArrayList.add(newIncident);

            return incidentArrayList;
        }


        public String getStringFromURL(String myURL) {
            StringBuilder sb = new StringBuilder();
            URLConnection urlConn = null;
            InputStreamReader in = null;
            try {
                URL url = new URL(myURL);
                urlConn = url.openConnection();
                if (urlConn != null)
                    urlConn.setReadTimeout(60000);
                if (urlConn != null && urlConn.getInputStream() != null) {
                    in = new InputStreamReader(urlConn.getInputStream(),
                            Charset.defaultCharset());
                    BufferedReader bufferedReader = new BufferedReader(in);
                    if (bufferedReader != null) {
                        int cp;
                        while ((cp = bufferedReader.read()) != -1) {
                            sb.append((char) cp);
                        }
                        bufferedReader.close();
                    }
                }
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Exception while calling URL:" + myURL);
            }

            return sb.toString();
        }

    }


}
