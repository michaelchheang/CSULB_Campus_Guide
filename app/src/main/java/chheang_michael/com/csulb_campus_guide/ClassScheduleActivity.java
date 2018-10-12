package chheang_michael.com.csulb_campus_guide;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ClassScheduleActivity extends AppCompatActivity {

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_schedule);

        fab = findViewById(R.id.addClassFloatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Add a course to your class schedule", Snackbar.LENGTH_SHORT)
                  //      .setAction("Action", null).show();

                //Create an AddClassActivity instance as a dialog
                startActivity(new Intent(ClassScheduleActivity.this, AddClassActivity.class));
            }
        });
    }
}
