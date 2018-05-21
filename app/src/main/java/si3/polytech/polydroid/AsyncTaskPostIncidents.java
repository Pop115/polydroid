package si3.polytech.polydroid;

import android.os.AsyncTask;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Kienan on 14/05/2018.
 */
class AsyncTaskPostIncidents extends AsyncTask<Incident, Void, Void> {

    public static String websiteAddr = "http://captainpop.alwaysdata.net/postIncident";

    @Override
    protected Void doInBackground(Incident... incidents) {

        String data = incidents[0].toString(); //data to post
        OutputStream out = null;
        try {
            URL url = new URL(websiteAddr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            out = new BufferedOutputStream(urlConnection.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.write(data);
            writer.flush();
            writer.close();
            out.close();
            urlConnection.connect();
        } catch (Exception e) {

            System.out.println(e.getMessage());


        }

        return null;
    }
}
