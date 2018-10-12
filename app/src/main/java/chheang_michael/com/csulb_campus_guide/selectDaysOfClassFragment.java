package chheang_michael.com.csulb_campus_guide;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;


public class selectDaysOfClassFragment extends DialogFragment{

    private CheckBox Sunday;
    private CheckBox Monday;
    private CheckBox Tuesday;
    private CheckBox Wednesday;
    private CheckBox Thursday;
    private CheckBox Friday;
    private CheckBox Saturday;
    private DaysOfClassListener listener;

////////////////////////////////////////////////////////////////////////////////

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.days_of_week, null);

        builder.setView(view)
                .setTitle(R.string.pick_days)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String s = "";
                        if(Sunday.isChecked()){
                            s += "Sunday, ";
                        }
                        if(Monday.isChecked()){
                            s += "Monday, ";
                        }
                        if(Tuesday.isChecked()){
                            s += "Tuesday, ";
                        }
                        if(Wednesday.isChecked()){
                            s += "Wednesday, ";
                        }
                        if(Thursday.isChecked()){
                            s += "Thursday, ";
                        }
                        if(Friday.isChecked()){
                            s += "Friday, ";
                        }
                        if(Saturday.isChecked()){
                            s += "Saturday, ";
                        }
                        if(s.length() > 2){
                            s = s.substring(0, s.length() - 2);
                        }
                        listener.applyDaysString(s);
                    }
                });
        Sunday = view.findViewById(R.id.sundayCheckBox);
        Monday = view.findViewById(R.id.mondayCheckBox);
        Tuesday = view.findViewById(R.id.tuesdayCheckBox);
        Wednesday = view.findViewById(R.id.wednesdayCheckBox);
        Thursday = view.findViewById(R.id.thursdayCheckBox);
        Friday = view.findViewById(R.id.fridayCheckBox);
        Saturday = view.findViewById(R.id.saturdayCheckBox);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (DaysOfClassListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement DaysOfClassListener");
        }

    }

    public interface DaysOfClassListener{
        void applyDaysString(String s);
    }
}
