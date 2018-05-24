package si3.polytech.polydroid;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Calendar;

public class SelectDateDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    TextView textView;

    public SelectDateDialogFragment(){
        this.textView = null;
    }

    @SuppressLint("ValidFragment")
    public SelectDateDialogFragment(TextView textView){
        this.textView = textView;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, yy, mm, dd);
    }

    public void onDateSet(DatePicker view, int yy, int mm, int dd) {
        populateSetDate(yy, mm + 1, dd);
    }

    public void populateSetDate(int year, int month, int day) {
        DecimalFormat mFormat = new DecimalFormat("00");
        textView.setText(mFormat.format(Double.valueOf(day)) + "/" + mFormat.format(Double.valueOf(month)) + "/" + (year));
    }

}