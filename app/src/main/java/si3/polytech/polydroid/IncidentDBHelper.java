package si3.polytech.polydroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class IncidentDBHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "polynews_database";
    private final Context myContext;
    private SQLiteDatabase myDataBase;

    public IncidentDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    public void openDataBase() throws SQLException, IOException {
        //Open the database
        String myPath = myContext.getDatabasePath(DB_NAME).getAbsolutePath();
        System.out.println(myPath);
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (!dbExist) {
            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();
            try {
                // Copy the database in assets to the application database.
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database", e);
            }
        }
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = myContext.getDatabasePath(DB_NAME).getAbsolutePath();
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            //database doesn't exist yet.
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null;
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = myContext.getDatabasePath(DB_NAME).getAbsolutePath();
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public List<Incident> getAllArticles() {
        ArrayList<Incident> incidentArrayList = new ArrayList<>();
        /*
        Cursor cursor = myDataBase.rawQuery("SELECT * FROM news ORDER BY date DESC", null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {


            Incident newArticle = new Incident();
            incidentArrayList.add(newArticle);
        }
        cursor.close();
        */
        Incident newIncident = new Incident(new Date(), "Moi", new Localisation("TEMPLIERS", "E+155", "jolie salle"), "Chaise cassée, gros problème!", "Chaise cassée", Importance.Critique, Type.ELEC);
        incidentArrayList.add(newIncident);
        incidentArrayList.add(newIncident);
        incidentArrayList.add(newIncident);
        incidentArrayList.add(newIncident);
        incidentArrayList.add(newIncident);
        incidentArrayList.add(newIncident);
        incidentArrayList.add(newIncident);
        incidentArrayList.add(newIncident);

        return incidentArrayList;
    }

}