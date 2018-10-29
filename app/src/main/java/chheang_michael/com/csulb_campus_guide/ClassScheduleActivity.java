package chheang_michael.com.csulb_campus_guide;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class ClassScheduleActivity extends AppCompatActivity{

    public static final int REQUEST_CODE_FOR_COURSE_INFO = 1337;
    public static final int RESULT_INVALID = 80085;
    FloatingActionButton fab;
  
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_schedule);

        fab = findViewById(R.id.addClassFloatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ClassScheduleActivity.this,
                        "Add a course to your schedule.", Toast.LENGTH_LONG)
                        .show();

                //Get course details through the add class activity dialog.
                Intent intent = new Intent(ClassScheduleActivity.this, AddClassActivity.class);
                startActivityForResult(intent, REQUEST_CODE_FOR_COURSE_INFO);
            }
        });

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Campus Map activity
                Intent intent = new Intent(ClassScheduleActivity.this, CampusMapActivity.class);
                startActivity(intent);
            }
        });

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(ClassScheduleActivity.this, CampusMapActivity.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case REQUEST_CODE_FOR_COURSE_INFO:
                if (resultCode == Activity.RESULT_OK) {
                    //Get the course details
                    ArrayList<String> arrayList= AddClassActivity.getCourseInfo(data);
                    String s = "";
                    for(int i = 0; i < arrayList.size(); i++){
                        s += arrayList.get(i) + "\n";
                    }
                    textView.setText(s);

                    Toast.makeText(ClassScheduleActivity.this,
                            "Successfully added a course!", Toast.LENGTH_LONG)
                            .show();
                }
                else if(resultCode == Activity.RESULT_CANCELED){
                    Toast.makeText(ClassScheduleActivity.this,
                            "Canceled!", Toast.LENGTH_LONG)
                            .show();
                }
                else if(resultCode == Activity.RESULT_FIRST_USER){
                    Toast.makeText(ClassScheduleActivity.this,
                            "Invalid or missing information!", Toast.LENGTH_LONG)
                            .show();
                }
                else{
                    Toast.makeText(ClassScheduleActivity.this,
                            "Failed to add the class...", Toast.LENGTH_LONG)
                            .show();
                }
        }
    }
}