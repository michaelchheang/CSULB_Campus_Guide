package chheang_michael.com.csulb_campus_guide;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

public class AddClassActivity extends AppCompatActivity {

    AutoCompleteTextView addClassInput;
    AutoCompleteTextView addBuildingInput;
    EditText roomNumberInput;
    EditText startTimeInput;
    EditText endTimeInput;
    CheckedTextView dateInput;
    Button addToSchedule;

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

        startTimeInput = (EditText) findViewById(R.id.startTimeEditText);
        startTimeInput.setFocusable(false);
        startTimeInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog startTimePicker = new TimePickerDialog( v.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        startTimeInput.setText(hourOfDay + ":" + minute);
                    }
                }, 0,0,false);
                startTimePicker.setTitle("Select Class Start Time");
                startTimePicker.show();
            }
        });
    }
}
