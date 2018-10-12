package chheang_michael.com.csulb_campus_guide;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

public class AddClassFragment extends DialogFragment{
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(R.layout.activity_add_class);
        builder.setPositiveButton(R.string.positive_action, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Add this course to the schedule

                //implement code to send new course information to parent activity
            }
        });
        builder.setNegativeButton(R.string.negative_action, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });

        // Create the AlertDialog object and return it
        return builder.create();
    }
}