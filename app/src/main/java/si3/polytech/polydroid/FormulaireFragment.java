package si3.polytech.polydroid;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Kienan on 16/04/2018.
 */

public class FormulaireFragment extends Fragment {

    public FormulaireFragment() {

    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static FormulaireFragment newInstance() {
        FormulaireFragment fragment = new FormulaireFragment();
        Bundle args = new Bundle();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.formulaire_declaration, container, false);
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if( requestCode == 1888 ) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ((ImageView)inflatedView.findViewById(R.id.image)).setImageBitmap(photo);
        }
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        Button addImageButton = (Button)inflatedView.findViewById(R.id.declarer_incident);
        addImageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 1888);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if( requestCode == 1888 ) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ((ImageView)inflatedView.findViewById(R.id.miniature)).setImageBitmap(photo);
        }
    }

}
