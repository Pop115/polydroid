package si3.polytech.polydroid;


import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;

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
        ImageView calendarImage = (ImageView)rootView.findViewById(R.id.calendarImage);
        calendarImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                DialogFragment dialogFragment = new SelectDateDialogFragment();
                dialogFragment.show(getFragmentManager(), "DatePicker");
            }
        });
        return rootView;
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if( requestCode == 1888 ) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ((ImageView)this.getView().findViewById(R.id.image)).setImageBitmap(photo);
        }
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        Button addImageButton = (Button)this.getView().findViewById(R.id.add_photo);
        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, 1888);
            }
        });
    }


    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int year, int month, int day) {

        }
    };


}
