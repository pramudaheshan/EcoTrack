package com.example.ecotrack.adminActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecotrack.R;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

public class CreateGarbageBinActivity extends AppCompatActivity {

    private FirebaseFirestore firestore;
    private CollectionReference driverCollection;
    private CollectionReference garbageBinCollection;

    private EditText areaEditText, cityEditText, bestRouteEditText;
    private TextView locationTextView;
    private Spinner loadTypeSpinner, cyclePeriodSpinner, assignDriverSpinner;
    private Button submitButton;

    private String selectedLoadType, selectedCyclePeriod, selectedDriverName;
    private double selectedLatitude = 6.9271;
    private double selectedLongitude = 79.8612;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_garbage_bin);


        firestore = FirebaseFirestore.getInstance();
        driverCollection = firestore.collection("driver");
        garbageBinCollection = firestore.collection("garbage_bin");


        areaEditText = findViewById(R.id.create_garbage_bin_area);
        cityEditText = findViewById(R.id.create_garbage_bin_city);
        bestRouteEditText = findViewById(R.id.create_garbage_bin_best_route);
        locationTextView = findViewById(R.id.selected_location); // Updated ID
        loadTypeSpinner = findViewById(R.id.create_garbage_bin_load_type);
        cyclePeriodSpinner = findViewById(R.id.create_garbage_bin_cycle_period);
        assignDriverSpinner = findViewById(R.id.create_garbage_bin_assign_driver); // Updated ID
        submitButton = findViewById(R.id.create_garbage_bin_submit_button);

        setupMap();
        setupSpinners();
        loadDriverNames();

        submitButton.setOnClickListener(v -> submitGarbageBinData());
    }

    private void setupMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_container);

        if (mapFragment != null) {
            mapFragment.getMapAsync(googleMap -> {

                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                        new com.google.android.gms.maps.model.LatLng(selectedLatitude, selectedLongitude), 12f));


                locationTextView.setText(String.format("Selected Location: %.6f, %.6f", selectedLatitude, selectedLongitude));


                googleMap.setOnMapClickListener(latLng -> {
                    selectedLatitude = latLng.latitude;
                    selectedLongitude = latLng.longitude;


                    locationTextView.setText(String.format("Selected Location: %.6f, %.6f", selectedLatitude, selectedLongitude));


                    googleMap.clear();
                    googleMap.addMarker(new com.google.android.gms.maps.model.MarkerOptions()
                            .position(latLng)
                            .title("Selected Location"));
                });
            });
        }
    }

    private void setupSpinners() {

        ArrayAdapter<CharSequence> loadTypeAdapter = ArrayAdapter.createFromResource(
                this, R.array.load_types, android.R.layout.simple_spinner_item);
        loadTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        loadTypeSpinner.setAdapter(loadTypeAdapter);
        loadTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedLoadType = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedLoadType = null;
            }
        });


        ArrayAdapter<CharSequence> cyclePeriodAdapter = ArrayAdapter.createFromResource(
                this, R.array.cycle_periods, android.R.layout.simple_spinner_item);
        cyclePeriodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cyclePeriodSpinner.setAdapter(cyclePeriodAdapter);
        cyclePeriodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCyclePeriod = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedCyclePeriod = null;
            }
        });
    }

    private void loadDriverNames() {
        driverCollection.get().addOnSuccessListener(queryDocumentSnapshots -> {
            ArrayAdapter<String> driverAdapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item);
            driverAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                String driverName = document.getString("name");
                if (driverName != null) {
                    driverAdapter.add(driverName);
                }
            }

            assignDriverSpinner.setAdapter(driverAdapter);
            assignDriverSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    selectedDriverName = parent.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    selectedDriverName = null;
                }
            });
        });
    }

    private void submitGarbageBinData() {
        String area = areaEditText.getText().toString().trim();
        String city = cityEditText.getText().toString().trim();
        String bestRoute = bestRouteEditText.getText().toString().trim();


        if (area.isEmpty() || city.isEmpty() || selectedLoadType == null || selectedLoadType.equals("Select Load Type") ||
                selectedCyclePeriod == null || selectedCyclePeriod.equals("Select Cycle Period") ||
                selectedDriverName == null || selectedDriverName.equals("Select Driver")) {
            Toast.makeText(this, "Please fill all fields.", Toast.LENGTH_SHORT).show();
            return;
        }


        String binId = "bin" + System.currentTimeMillis();


        Map<String, Object> garbageBinData = new HashMap<>();
        garbageBinData.put("bin_id", binId);
        garbageBinData.put("area", area);
        garbageBinData.put("city", city);
        garbageBinData.put("best_route", bestRoute);
        garbageBinData.put("cycle_period", selectedCyclePeriod);
        garbageBinData.put("driver_name", selectedDriverName);
        garbageBinData.put("load_type", selectedLoadType);
        garbageBinData.put("geolocation", new com.google.firebase.firestore.GeoPoint(selectedLatitude, selectedLongitude));


        System.out.println("Submitting Garbage Bin Data: " + garbageBinData);


        garbageBinCollection.document(binId).set(garbageBinData)
                .addOnSuccessListener(unused -> {
                    Log.d("GarbageBinActivity", "Data successfully written: " + garbageBinData);
                    Toast.makeText(this, "Garbage Bin Added Successfully", Toast.LENGTH_SHORT).show();
                    clearForm();
                })
                .addOnFailureListener(e -> {
                    Log.e("GarbageBinActivity", "Error writing document", e);
                    Toast.makeText(this, "Error saving data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private void clearForm() {
        areaEditText.setText("");
        cityEditText.setText("");
        bestRouteEditText.setText("");
        loadTypeSpinner.setSelection(0);
        cyclePeriodSpinner.setSelection(0);
        assignDriverSpinner.setSelection(0);
        locationTextView.setText("Selected Location: Lat, Long");
    }
}