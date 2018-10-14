package chheang_michael.com.csulb_campus_guide;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.ArrayList;

public class AddClassActivity extends AppCompatActivity implements selectDaysOfClassFragment.DaysOfClassListener {

    public static final String RESULT_COURSE_INFO = "chheang_michael.com.csulb_campus_guide.AddClassActivity - Return Course Info";
    AutoCompleteTextView addClassInput;
    AutoCompleteTextView addBuildingInput;
    EditText roomNumberInput;
    EditText startTimeInput;
    EditText endTimeInput;
    EditText dateInput;
    Button addToSchedule;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        // addClassInput will ask the user to enter the course's name (e.g. "CECS 445" or "ENGL 317")
        addClassInput = (AutoCompleteTextView) findViewById(R.id.classesAutoCompleteTextView);
        ArrayAdapter<String> classesAdapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.classes));
        addClassInput.setAdapter(classesAdapter);
        addClassInput.setThreshold(1);

        // addBuildingInput will ask the user to enter the course's building (e.g. "ECS" or "Engineering and Computer Science")
        addBuildingInput = (AutoCompleteTextView) findViewById(R.id.buildingsAutoCompleteTextView);
        ArrayAdapter<String> buildingsAdapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.buildings));
        addBuildingInput.setAdapter(buildingsAdapter);
        addBuildingInput.setThreshold(1);

        // startTimeInput will hold the user's entry for the class's start time. The user will use a TimePickerDialog to select the time.
        startTimeInput = (EditText) findViewById(R.id.startTimeEditText);
        startTimeInput.setFocusable(false);
        startTimeInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog startTimePicker = new TimePickerDialog( v.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if(hourOfDay == 0){
                            startTimeInput.setText("12" + ":" + String.format("%1$02d", minute) + " AM");
                        }
                        else if(hourOfDay <= 11){
                            startTimeInput.setText(hourOfDay + ":" + String.format("%1$02d", minute) + " AM");
                        }
                        else if(hourOfDay == 12){
                            startTimeInput.setText(hourOfDay + ":" + String.format("%1$02d", minute) + " PM");
                        }
                        else {
                            startTimeInput.setText(hourOfDay - 12 + ":" + String.format("%1$02d", minute) + " PM");
                        }
                    }
                }, 0,0,false);
                startTimePicker.setTitle("Select Class Start Time");
                startTimePicker.show();
            }
        });

        // endTimeInput will hold the user's entry for the class's end time. The user will use a TimePickerDialog to select the time.
        endTimeInput = (EditText) findViewById(R.id.endTimeEditText);
        endTimeInput.setFocusable(false);
        endTimeInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog endTimePicker = new TimePickerDialog( v.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if(hourOfDay == 0){
                            endTimeInput.setText("12" + ":" + String.format("%1$02d", minute) + " AM");
                        }
                        else if(hourOfDay <= 11){
                            endTimeInput.setText(hourOfDay + ":" + String.format("%1$02d", minute) + " AM");
                        }
                        else if(hourOfDay == 12){
                            endTimeInput.setText(hourOfDay + ":" + String.format("%1$02d", minute) + " PM");
                        }
                        else {
                            endTimeInput.setText(hourOfDay - 12 + ":" + String.format("%1$02d", minute) + " PM");
                        }
                    }
                }, 0,0,false);
                endTimePicker.setTitle("Select Class end Time");
                endTimePicker.show();
            }
        });

        dateInput = (EditText) findViewById(R.id.daysOfClassEditText);
        dateInput.setFocusable(false);
        dateInput.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new selectDaysOfClassFragment();
                newFragment.show(getFragmentManager(), "class_days");
            }
        });

        roomNumberInput = (EditText) findViewById(R.id.roomNumberEditText);

        addToSchedule = (Button) findViewById(R.id.addToScheduleButton);
        addToSchedule.setText("Add to Schedule");
        addToSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Extract data from the UI:
                ArrayList<String> arrayList = new ArrayList<String>();

                arrayList.add("Course: " + addClassInput.getText().toString());
                arrayList.add("Building: " + addBuildingInput.getText().toString());
                arrayList.add("Room: " + roomNumberInput.getText().toString());
                arrayList.add("Start Time: " + startTimeInput.getText().toString());
                arrayList.add("End Time: " + endTimeInput.getText().toString());
                arrayList.add("Days of Class: " + dateInput.getText().toString());

                // Pass data back to schedule activity
                Intent intent = new Intent();
                intent.putExtra(RESULT_COURSE_INFO, arrayList);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        cancel = (Button) findViewById(R.id.cancelButton);
        cancel.setText("Cancel");

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //close the activity when cancel is clicked
                Intent intent = new Intent();
                setResult(Activity.RESULT_CANCELED, intent);
                finish();
            }
        });
    }

    public static ArrayList<String> getCourseInfo(Intent intent){
        return intent.getStringArrayListExtra(RESULT_COURSE_INFO);
    }

    @Override
    public void applyDaysString(String s) {
        dateInput.setText(s);
    }
}
