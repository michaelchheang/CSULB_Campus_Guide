package chheang_michael.com.csulb_campus_guide;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Objects;

public class NotificationsFragment extends DialogFragment implements AdapterView.OnItemSelectedListener {
    private EditText editText;
    private String selection;
    private Switch aSwitch;
    private Spinner spinner;
    private String notificationFlag;
    private static final String FILE_NAME = "courseInfoFile.txt";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        final String toChange = bundle.getString("toChange");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_notification, null);

        aSwitch = view.findViewById(R.id.switch1);
        editText = view.findViewById(R.id.editTextNotifyTime);
        spinner = view.findViewById(R.id.spinnerTime);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) { notificationFlag = "true"; }
                else { notificationFlag = "false"; }}
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.timeUnits, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        setDialogSettings();

        builder.setView(view)
                .setTitle("Set Notifications For This Course")
                .setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Get input
                        String notifyString = getNotifyString();

                        //course to be changed
                        String[] parsedToChange = toChange.split(";");
                        StringBuilder toReplace = new StringBuilder();
                        for(int i = 0; i < parsedToChange.length; i++){
                            if(i < 7){ toReplace.append(parsedToChange[i]).append(";"); }}
                        toReplace.append(notifyString).append("\n");

                        // read file and add the unchanging lines to temp but replace the one that needs to change
                        FileInputStream fis;
                        StringBuilder temp = new StringBuilder();
                        if(fileExist(FILE_NAME)) {
                            try {
                                fis = getActivity().openFileInput(FILE_NAME);
                                InputStreamReader isr = new InputStreamReader(fis);
                                BufferedReader reader = new BufferedReader(isr);
                                String line;
                                while ((line = reader.readLine()) != null) {
                                    if(!Objects.equals(line, toChange)) {temp.append(line).append("\n"); }
                                    else{ temp.append(toReplace); }
                                }
                            }catch (IOException e) {/*do nothing*/}
                        }
                        else{new File(getActivity().getFilesDir(), FILE_NAME);}

                        //delete contents from file
                        PrintWriter pw = null;
                        try {
                            pw = new PrintWriter("/data/data/chheang_michael.com.csulb_campus_guide/files/courseInfoFile.txt");
                            pw.write("");
                        }catch (FileNotFoundException e) {e.printStackTrace(); }
                        pw.close();

                        //write new content to file
                        FileOutputStream outputStream = null;
                        try {
                            outputStream = getActivity().openFileOutput(FILE_NAME, Context.MODE_APPEND);
                            outputStream.write(temp.toString().getBytes());
                        }catch (Exception e){e.printStackTrace();
                        }finally {
                            if(outputStream != null){
                                try {outputStream.close();}
                                catch (IOException e) {e.printStackTrace();}
                            }
                        }
                        dismiss();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {/*User cancelled the dialog*/}});

        return builder.create();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position == 0){ selection = "minute(s)"; }
        else { selection = "hour(s)"; } }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { selection = "minute(s)"; }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void setDialogSettings() {
        Bundle b = getArguments();
        String s = b.getString("toChange");
        String[] parsedString = s.split(";");

        if (Objects.equals(parsedString[7], "true")) {
            aSwitch.setChecked(true);
        } else {
            aSwitch.setChecked(false);
        }

        editText.setText(parsedString[8]);

        if (Objects.equals(parsedString[9], "hour(s)")) {
            spinner.setSelection(1);
        } else {
            spinner.setSelection(0);
        }
    }

    public String getNotifyString(){
        String notifyString = "";
        if(aSwitch.isChecked()){ notifyString += "true;"; }
        else{ notifyString += "false;"; }

        notifyString += editText.getText() + ";";

        if(spinner.getLastVisiblePosition() == 0){ notifyString += "minute(s);"; }
        else{ notifyString += "hour(s);";
        }
        return notifyString;
    }

    public boolean fileExist(String fname){
        File file = getActivity().getFileStreamPath(fname);
        return file.exists();
    }
}