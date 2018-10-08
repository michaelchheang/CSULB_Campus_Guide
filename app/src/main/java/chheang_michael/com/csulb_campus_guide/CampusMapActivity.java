package chheang_michael.com.csulb_campus_guide;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class CampusMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //This variable holds the coordinates for the middle of CSULB's campus
        LatLng csulbCampus = new LatLng(33.783832, -118.114230);

        //Add a marker for CSULB campus.
        mMap.addMarker(new MarkerOptions().position(csulbCampus).title("Marker in CSULB"));

        //Limit user scrolling/panning to within the CSULB campus
        LatLngBounds csulbBounds = new LatLngBounds(
                new LatLng(33.775093, -118.121279), new LatLng(33.788692, -118.107953));
        mMap.setLatLngBoundsForCameraTarget(csulbBounds);

        //Set minimum zoom level for map camera.
        mMap.setMinZoomPreference(14.0f);

        //Move the camera to the CSULB campus.
        mMap.moveCamera(CameraUpdateFactory.newLatLng(csulbCampus));
    }
}
