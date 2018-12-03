package chheang_michael.com.csulb_campus_guide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ClassScheduleActivity extends AppCompatActivity{

    public static final int REQUEST_CODE_FOR_COURSE_INFO = 1337;
    private static final String FILE_NAME = "courseInfoFile.txt";
    private CourseDataAdapter mAdapter;
    SwipeController swipeController = null;
    FloatingActionButton fab;
    FloatingActionButton campusMapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_schedule);

        setTitle("CSULB Campus Guide");

        fab = findViewById(R.id.addClassFloatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ClassScheduleActivity.this,
                        "Add a course to your schedule.", Toast.LENGTH_LONG)
                        .show();

                //Get course details through the add class activity dialog.
                Intent intent = new Intent(ClassScheduleActivity.this, AddCourseActivity.class);
                startActivityForResult(intent, REQUEST_CODE_FOR_COURSE_INFO);
            }
        });

        campusMapButton = findViewById(R.id.campusMapFloatingActionButton);
        campusMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Campus Map activity
                Intent intent = new Intent(ClassScheduleActivity.this, CampusMapActivity.class);
                startActivity(intent);
            }
        });

        setCourseDataAdapter();
        setupRecyclerView();
    }

    private void setCourseDataAdapter(){
        List<Course> courses = new ArrayList<>();
        FileInputStream fis = null;
        if(fileExist(FILE_NAME)) {
            try {
                fis = openFileInput(FILE_NAME);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader reader = new BufferedReader(isr);
                String line;
                String[] courseInfoSection;
                while ((line = reader.readLine()) != null) {
                    courseInfoSection = line.split(";");
                    Course course = new Course();
                    course.setCourseSubject(courseInfoSection[0]);
                    course.setCourseNumber(courseInfoSection[1]);
                    course.setBuilding(courseInfoSection[2]);
                    course.setRoom(courseInfoSection[3]);
                    course.setStartTime(courseInfoSection[4]);
                    course.setEndTime(courseInfoSection[5]);
                    course.setDaysOfClass(courseInfoSection[6]);
                    courses.add(course);
                }
            } catch (IOException e) {
                //do nothing}
            }
        }
        else{
            new File(getBaseContext().getFilesDir(), FILE_NAME);
        }
        mAdapter = new CourseDataAdapter(courses);
    }

    private void setupRecyclerView(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.classRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);
        swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {
                mAdapter.courses.remove(position);
                mAdapter.notifyItemRemoved(position);
                mAdapter.notifyItemRangeChanged(position, mAdapter.getItemCount());
            }

            @Override
            public void onLeftClicked(int position){
                //map button
                Toast.makeText(ClassScheduleActivity.this,
                        "PRESSED MAP!!!", Toast.LENGTH_LONG)
                        .show();
            }

            @Override
            public void onLeftClicked2(int position){
                //alarm button
                Toast.makeText(ClassScheduleActivity.this,
                        "PRESSED ALARM!!!", Toast.LENGTH_LONG)
                        .show();
            }
        });

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case REQUEST_CODE_FOR_COURSE_INFO:
                if (resultCode == Activity.RESULT_OK) {
                    //Get the course details
                    ArrayList<String> arrayList= AddCourseActivity.getCourseInfo(data);
                    String courseInfo = "";
                    for(int i = 0; i < arrayList.size(); i++){
                        courseInfo += arrayList.get(i) + ";";
                    }
                    courseInfo += "\n";

                    FileOutputStream outputStream = null;
                    try {
                        outputStream = openFileOutput(FILE_NAME, Context.MODE_APPEND);
                        outputStream.write(courseInfo.getBytes());
                    } catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        if(outputStream != null){
                            try {
                                outputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    setCourseDataAdapter();
                    setupRecyclerView();

                    Toast.makeText(ClassScheduleActivity.this,
                            "Successfully added a course!", Toast.LENGTH_LONG)
                            .show();
                }
                else if(resultCode == Activity.RESULT_CANCELED){
                    Toast.makeText(ClassScheduleActivity.this,
                            "Canceled!", Toast.LENGTH_LONG)
                            .show();
                }
                else{
                    Toast.makeText(ClassScheduleActivity.this,
                            "Failed to add the class...", Toast.LENGTH_LONG)
                            .show();
                }
        }
    }

    public boolean fileExist(String fname){
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();
    }
}

