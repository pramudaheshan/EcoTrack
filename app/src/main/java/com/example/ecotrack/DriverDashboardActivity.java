package com.example.ecotrack;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecotrack.driverActivity.BestRouteActivity;
import com.example.ecotrack.driverActivity.UpdateGarbageLoadActivity;
import com.example.ecotrack.driverActivity.WorkUpdatesActivity;

public class DriverDashboardActivity extends AppCompatActivity {

    private Button btnCheckWorkUpdates, btnChooseBestRoute, btnUpdateGarbageLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_dashboard);

        // Initialize Buttons
        btnCheckWorkUpdates = findViewById(R.id.btn_check_work_updates);
        btnChooseBestRoute = findViewById(R.id.btn_choose_best_route);
        btnUpdateGarbageLoad = findViewById(R.id.btn_update_garbage_load);

        // Set Click Listeners
        btnCheckWorkUpdates.setOnClickListener(v -> {
            // Open Daily Work Updates Activity
            Intent intent = new Intent(DriverDashboardActivity.this, WorkUpdatesActivity.class);
            startActivity(intent);
        });

        btnChooseBestRoute.setOnClickListener(v -> {
            // Open Best Route Activity
            Intent intent = new Intent(DriverDashboardActivity.this, BestRouteActivity.class);
            startActivity(intent);
        });

        btnUpdateGarbageLoad.setOnClickListener(v -> {
            // Open Update Garbage Load Activity
            Intent intent = new Intent(DriverDashboardActivity.this, UpdateGarbageLoadActivity.class);
            startActivity(intent);
        });
    }
}