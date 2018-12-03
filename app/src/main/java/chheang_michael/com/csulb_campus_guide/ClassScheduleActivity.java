package chheang_michael.com.csulb_campus_guide;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClassScheduleActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_FOR_COURSE_INFO = 1337;
    private static final String FILE_NAME = "courseInfoFile.txt";
    private CourseDataAdapter mAdapter;
    SwipeController swipeController = null;
    FloatingActionButton fab;
    FloatingActionButton campusMapButton;
    HashMap<String, LatLng> hMap = new HashMap<String, LatLng>();
    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_schedule);

        hMap.put("Library - LIB", new LatLng(33.777196, -118.114788));
        hMap.put("Liberal Arts 1 - LA1", new LatLng(33.777649, -118.114723));
        hMap.put("Liberal Arts 2 - LA2", new LatLng(33.777930, -118.114583));
        hMap.put("Liberal Arts 3 - LA3", new LatLng(33.778273, -118.114524));
        hMap.put("Liberal Arts 4 - LA4", new LatLng(33.778549, -118.114406));
        hMap.put("Liberal Arts 5 - LA5", new LatLng(33.778913, -118.114256));
        hMap.put("Psychology - PSY", new LatLng(33.779381, -118.114175));
        hMap.put("Education 1 - ED1", new LatLng(33.776204, -118.114060));
        hMap.put("Education 2 - ED2", new LatLng(33.775736, -118.114347));
        hMap.put("Theater Arts - TA", new LatLng(33.776467, -118.112532));
        hMap.put("Language Arts Building - LAB", new LatLng(33.776840, -118.112666));
        hMap.put("Fine Arts 1 - FA1", new LatLng(33.777205, -118.112516));
        hMap.put("Fine Arts 2 - FA2", new LatLng(33.777511, -118.112432));
        hMap.put("Fine Arts 3 - FA3", new LatLng(33.777880, -118.112233));
        hMap.put("Fine Arts 4 - FA4", new LatLng(33.778157, -118.112749));
        hMap.put("Peterson Hall 1 - PH1", new LatLng(33.778817, -118.112577));
        hMap.put("Peterson Hall 2 - PH2", new LatLng(33.779289, -118.112470));
        hMap.put("Hall of Science - HSCI", new LatLng(33.779798, -118.112582));
        hMap.put("Molecular &amp; Life Sciences Center - MLSC", new LatLng(33.780163, -118.112303));
        hMap.put("Nursing - NUR", new LatLng(33.781617, -118.117771));
        hMap.put("Family &amp; Consumer Sciences - FCS", new LatLng(33.781599, -118.116149));
        hMap.put("College of Business Administration - CBA", new LatLng(33.783993, -118.115896));
        hMap.put("Kinesology - KIN", new LatLng(33.783003, -118.113131));
        hMap.put("Health &amp; Human Services 1 Classrooms - HHS1", new LatLng(33.782317, -118.112701));
        hMap.put("Health &amp; Human Services 2 Classrooms - HHS2", new LatLng(33.782308, -118.112228));
        hMap.put("Social Sciences/Public Administration - SS/PA", new LatLng(33.781956, -118.110499));
        hMap.put("Vivian Engineering Center - VEC", new LatLng(33.782812, -118.110409));
        hMap.put("Engineering 2 - EN2", new LatLng(33.783151, -118.110678));
        hMap.put("Engineering 3 - EN3", new LatLng(33.783623, -118.111172));
        hMap.put("Engineering 4 - EN4", new LatLng(33.783579, -118.110731));
        hMap.put("Engineering/Computer Sciences - ECS", new LatLng(33.783481, -118.110269));
        hMap.put("Design - DESN", new LatLng(33.782045, -118.109453));
        hMap.put("Human Services and Design - HSD", new LatLng(33.782678, -118.109593));
        hMap.put("Engineering Technology - ET", new LatLng(33.782838, -118.108905));
        hMap.put("Microbiology - MIC", new LatLng(33.779296, -118.111747));

        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            return;
        }

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 00, 00, locationListener);
            }
        }
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
                //what map button does upon a click
                Course course = mAdapter.courses.get(position);
                Toast.makeText(ClassScheduleActivity.this,
                        course.getBuilding(), Toast.LENGTH_LONG)
                        .show();
                LatLng location = hMap.get(course.getBuilding());
                launchNavigation(location.latitude, location.longitude);
            }

            @Override
            public void onLeftClicked2(int position){
                //what the alarm button when clicked
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

    public void launchNavigation(double mLat, double mLng){
        String location = mLat + "," + mLng;
        Uri gmmIntentUri = Uri.parse("google.navigation:q="+location+"&mode=w");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}

