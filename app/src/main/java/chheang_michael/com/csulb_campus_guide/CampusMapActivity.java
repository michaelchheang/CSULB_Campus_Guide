package chheang_michael.com.csulb_campus_guide;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
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

<<<<<<< HEAD
        //set of Lat Long to store locations of markers.
        LatLng lib = new LatLng(33.777196, -118.114788); // campus library
        LatLng la1 = new LatLng(33.777649, -118.114723); // liberal arts 1
        LatLng la2 = new LatLng(33.777930, -118.114583); // liberal arts 2
        LatLng la3 = new LatLng(33.778273, -118.114524); // liberal arts 3
        LatLng la4 = new LatLng(33.778549, -118.114406); // liberal arts 4
        LatLng la5 = new LatLng(33.778913, -118.114256); // liberal arts 5
        LatLng psy = new LatLng(33.779381, -118.114175); // psychology
        LatLng ed1 = new LatLng(33.776204, -118.114060); // education 1
        LatLng ed2 = new LatLng(33.775736, -118.114347); // education 2
        LatLng ta = new LatLng(33.776467, -118.112532);  // theatre arts
        LatLng lab = new LatLng(33.776840, -118.112666); // language arts building
        LatLng fa1 = new LatLng(33.777205, -118.112516); // fine arts 1
        LatLng fa2 = new LatLng(33.777511, -118.112432); // fine arts 2
        LatLng fa3 = new LatLng(33.777880, -118.112233); // fine arts 3
        LatLng fa4 = new LatLng(33.778157, -118.112749); // fine arts 4
        LatLng ph1 = new LatLng(33.778817, -118.112577); // peterson hall 1
        LatLng ph2 = new LatLng(33.779289, -118.112470); // peterson hall 2
        LatLng hsci = new LatLng(33.779798, -118.112582);// hall of science
        LatLng mlsc = new LatLng(33.780163, -118.112303);// molecular & life sciences center
        LatLng nur = new LatLng(33.781617, -118.117771); // nursing
        LatLng fcs = new LatLng(33.781599, -118.116149); // family & consumer sciences
        LatLng cba = new LatLng(33.783993, -118.115896); // college of business administration
        LatLng kin = new LatLng(33.783003, -118.113131); // kinesology
        LatLng hhs1 = new LatLng(33.782317, -118.112701);// health & human services 1
        LatLng hhs2 = new LatLng(33.782308, -118.112228);// health & human services 2
        LatLng sspa = new LatLng(33.781956, -118.110499);// social sciences/public administration
        LatLng vec = new LatLng(33.782812, -118.110409); // vivian engineering center
        LatLng en2 = new LatLng(33.783151, -118.110678); // engineering 2
        LatLng en3 = new LatLng(33.783623, -118.111172); // engineering 3
        LatLng en4 = new LatLng(33.783579, -118.110731); // engineering 4
        LatLng ecs = new LatLng(33.783481, -118.110269); // engineering/computer sciences
        LatLng desn = new LatLng(33.782045, -118.109453);// design
        LatLng hsd = new LatLng(33.782678, -118.109593); // human services & design
        LatLng et = new LatLng(33.782838, -118.108905);  // engineering technology
        LatLng mic = new LatLng(33.779296, -118.111747); // microbiology

        //Add a marker in CSULB and move the camera
=======
        //This variable holds the coordinates for the middle of CSULB's campus
>>>>>>> master
        LatLng csulbCampus = new LatLng(33.783832, -118.114230);

        //Add a marker for CSULB campus.
        mMap.addMarker(new MarkerOptions().position(csulbCampus).title("Marker in CSULB"));

        //Limit user scrolling/panning to within the CSULB campus
        LatLngBounds csulbBounds = new LatLngBounds(
                new LatLng(33.775093, -118.121279), new LatLng(33.788692, -118.107953));
        mMap.setLatLngBoundsForCameraTarget(csulbBounds);

<<<<<<< HEAD
        mMap.setMinZoomPreference(17.0f);
=======
        //Set minimum zoom level for map camera.
        mMap.setMinZoomPreference(14.0f);
>>>>>>> master

        //Move the camera to the CSULB campus.
        mMap.moveCamera(CameraUpdateFactory.newLatLng(csulbCampus));

        // add markers to the campus map display
        mMap.addMarker(new MarkerOptions().position(lib).title("CSULB Campus Library")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions().position(la1).title("Liberal Arts 1")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions().position(la2).title("Liberal Arts 2")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions().position(la3).title("Liberal Arts 3")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions().position(la4).title("Liberal Arts 4")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions().position(la5).title("Liberal Arts 5")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions().position(psy).title("Psychology")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions().position(ed1).title("Education 1")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions().position(ed2).title("Education 2")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions().position(ta).title("Theatre Arts")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions().position(lab).title("Language Arts Building")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions().position(fa1).title("Fine Arts 1")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions().position(fa2).title("Fine Arts 2")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions().position(fa3).title("Fine Arts 3")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions().position(fa4).title("Fine Arts 4")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions().position(ph1).title("Peterson Hall 1")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions().position(ph2).title("Peterson Hall 2")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions().position(hsci).title("Hall of Science")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions().position(mlsc).title("Molecular & Life Sciences Center")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions().position(nur).title("Nursing")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions().position(fcs).title("Family & Consumer Sciences")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions().position(cba).title("College of Business Administration")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions().position(kin).title("Kinesology")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions().position(hhs1).title("Health & Human Services 1")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions().position(hhs2).title("Health & Human Services 2")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions().position(sspa).title("Social Sciences/Public Administration")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions().position(vec).title("Vivian Engineering Center")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions().position(en2).title("Engineering 2")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions().position(en3).title("Engineering 3")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions().position(en4).title("Engineering 4")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions().position(ecs).title("Engineering/Computer Sciences")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions().position(desn).title("Design")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions().position(hsd).title("Human Services & Design")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions().position(et).title("Engineering Technology")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions().position(mic).title("Microbiology")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
    }
}
