package chheang_michael.com.csulb_campus_guide;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class AddCourseActivity extends AppCompatActivity implements selectDaysOfClassFragment.DaysOfClassListener {

    public static final String RESULT_COURSE_INFO = "chheang_michael.com.csulb_campus_guide.AddCourseActivity - Return Course Info";
    AutoCompleteTextView addCourseInput;
    AutoCompleteTextView addBuildingInput;
    EditText courseNumberInput;
    EditText roomNumberInput;
    EditText startTimeInput;
    EditText endTimeInput;
    EditText daysOfCourseInput;
    Button addToSchedule;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        // addCourseInput will ask the user to enter the course's name (e.g. "CECS 445" or "ENGL 317")
        addCourseInput = (AutoCompleteTextView) findViewById(R.id.coursesAutoCompleteTextView);
        ArrayAdapter<String> coursesAdapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.courses));
        addCourseInput.setAdapter(coursesAdapter);
        addCourseInput.setThreshold(1);

        courseNumberInput = (EditText) findViewById(R.id.courseNumberEditText);

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
                startTimePicker.setTitle("Select Course Start Time");
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
                endTimePicker.setTitle("Select Course end Time");
                endTimePicker.show();
            }
        });

        daysOfCourseInput = (EditText) findViewById(R.id.daysOfCourseEditText);
        daysOfCourseInput.setFocusable(false);
        daysOfCourseInput.setOnClickListener(new View.OnClickListener(){
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
                // Extract data from the dialog:
                ArrayList<String> arrayList = new ArrayList<String>();

                String course = addCourseInput.getText().toString();
                if(Arrays.binarySearch(getResources().getStringArray(R.array.courses), course) > 0){
                    // do nothing if course matches a class from array
                }
                else{
                    Toast.makeText(AddCourseActivity.this,
                            "Please enter a valid course", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                String courseNumber = courseNumberInput.getText().toString();
                if(TextUtils.isEmpty(courseNumber)){
                    Toast.makeText(AddCourseActivity.this,
                            "Please enter course number.", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                String building = addBuildingInput.getText().toString();
                if(Arrays.binarySearch(getResources().getStringArray(R.array.buildings), building) > 0) {
                    // do nothing if input matches a building in array
                }
                else{
                    Toast.makeText(AddCourseActivity.this,
                            "Please enter a valid building.", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                String room = roomNumberInput.getText().toString();
                if(TextUtils.isEmpty(room)){
                    Toast.makeText(AddCourseActivity.this,
                            "Please enter room number.", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                String startTime = startTimeInput.getText().toString();
                if(TextUtils.isEmpty(startTime)){
                    Toast.makeText(AddCourseActivity.this,
                            "Please enter start time.", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                String endTime = endTimeInput.getText().toString();
                if(TextUtils.isEmpty(endTime)){
                    Toast.makeText(AddCourseActivity.this,
                            "Please enter end time.", Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                String daysOfWeek = daysOfCourseInput.getText().toString();
                if(TextUtils.isEmpty(daysOfWeek)){
                    Toast.makeText(AddCourseActivity.this,
                            "Select days of class meetings.", Toast.LENGTH_LONG)
                            .show();
                    return;
                }
                else {
                    arrayList.add(course);
                    arrayList.add(courseNumber);
                    arrayList.add(building);
                    arrayList.add(room);
                    arrayList.add(startTime);
                    arrayList.add(endTime);
                    arrayList.add(daysOfWeek);

                    // Pass valid data back to schedule activity
                    Intent intent = new Intent();
                    intent.putExtra(RESULT_COURSE_INFO, arrayList);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
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
        daysOfCourseInput.setText(s);
    }
}
