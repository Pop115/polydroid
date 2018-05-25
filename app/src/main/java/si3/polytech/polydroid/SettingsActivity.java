package si3.polytech.polydroid;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SettingsActivity  extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parametres);

        SharedPreferences prefs = getSharedPreferences("label", 0);
        String name = prefs.getString("name", "TestAuteur");

        Button valid = (Button)findViewById(R.id.validButton);
        valid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs2 = getSharedPreferences("label", 0);
                SharedPreferences.Editor editor = prefs2.edit();
                EditText editName = (EditText)findViewById(R.id.editName);
                String changedName = editName.getText().toString();
                editor.putString("name", changedName);
                System.out.println(changedName);
            }
        });
    }



}
