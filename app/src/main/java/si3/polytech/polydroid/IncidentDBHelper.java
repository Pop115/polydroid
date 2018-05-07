package si3.polytech.polydroid;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class IncidentDBHelper {



    public List<Incident> getAllArticles() {
        ArrayList<Incident> incidentArrayList = new ArrayList<>();
        JSONArray incidentJSON = null;
        String stringFromURL = "";
        try {
            AsyncTask<Object, Object, String> asyncTask = new AsyncTask() {
                @Override
                protected Object doInBackground(Object[] objects) {
                    return getStringFromURL("http://192.168.43.66:3000/incidents");
                }
            };
            stringFromURL = asyncTask.execute().get();
            incidentJSON = new JSONArray(stringFromURL);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for(int i = 0; i<incidentJSON.length(); i++){
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

        /*
        Cursor cursor = myDataBase.rawQuery("SELECT * FROM Incidents", null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            System.out.println(cursor);
        }
        cursor.close();
        */

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
            System.err.println("Exception while calling URL:"+ myURL);
        }

        return sb.toString();
    }

}