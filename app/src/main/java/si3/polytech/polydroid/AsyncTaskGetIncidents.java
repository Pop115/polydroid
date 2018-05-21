package si3.polytech.polydroid;

import android.os.AsyncTask;

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
 * Created by Kienan on 14/05/2018.
 */
class AsyncTaskGetIncidents extends AsyncTask<Void, Void, ArrayList<Incident>> {
    @Override
    protected ArrayList<Incident> doInBackground(Void... voids) {
        return null;
    }
/*
    @Override
    protected ArrayList<Incident> doInBackground(Void... voids) {
        try {
            return getAllIncidents();
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
            IncidentFragment.adapter.incidentArrayList.addAll(result);
        IncidentFragment.adapter.notifyDataSetChanged();
    }

    public ArrayList<Incident> getAllIncidents() throws JSONException {
        ArrayList<Incident> incidentArrayList = new ArrayList<>();
        JSONArray incidentJSON = null;
        String stringFromURL = getStringFromURL("http://captainpop.alwaysdata.net/incidents");

        incidentJSON = new JSONArray(stringFromURL);


        for (int i = 0; i < incidentJSON.length(); i++) {
            try {
                JSONObject jsonObject = incidentJSON.getJSONObject(i);
                System.out.println(jsonObject);
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
*/
}
