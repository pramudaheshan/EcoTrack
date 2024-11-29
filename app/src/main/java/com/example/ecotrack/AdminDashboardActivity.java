package com.example.ecotrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ecotrack.adminActivity.AssignBestRouteActivity;
import com.example.ecotrack.adminActivity.CreateGarbageBinActivity;
import com.example.ecotrack.adminActivity.ManageDriverActivity;
import com.example.ecotrack.adminActivity.UpdateDeleteGarbageBinActivity;
import com.example.ecotrack.adminActivity.ViewComplaintsActivity;
import com.example.ecotrack.adminActivity.ViewGarbageReportActivity;

public class AdminDashboardActivity extends AppCompatActivity {

    // Declare buttons
    private Button btnCreateGarbageBin;
    private Button btnUpdateDeleteGarbageBin;
    private Button btnAssignBestRoute;
    private Button btnManageDriver;
    private Button btnViewGarbageReport;
    private Button btnViewComplaints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        // Initialize buttons
        btnCreateGarbageBin = findViewById(R.id.btn_create_garbage_bin);
        btnUpdateDeleteGarbageBin = findViewById(R.id.btn_update_delete_garbage_bin);
        btnAssignBestRoute = findViewById(R.id.btn_assign_best_route);
        btnManageDriver = findViewById(R.id.btn_manage_driver);
        btnViewGarbageReport = findViewById(R.id.btn_view_garbage_report);
        btnViewComplaints = findViewById(R.id.btn_view_complaints);

        // Set click listeners for buttons
        btnCreateGarbageBin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Create Garbage Bin activity
                Intent intent = new Intent(AdminDashboardActivity.this, CreateGarbageBinActivity.class);
                startActivity(intent);
            }
        });

        btnUpdateDeleteGarbageBin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Update/Delete Garbage Bin activity
                Intent intent = new Intent(AdminDashboardActivity.this, UpdateDeleteGarbageBinActivity.class);
                startActivity(intent);
            }
        });

        btnAssignBestRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Assign Best Route activity
                Intent intent = new Intent(AdminDashboardActivity.this, AssignBestRouteActivity.class);
                startActivity(intent);
            }
        });

        btnManageDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Manage Driver activity
                Intent intent = new Intent(AdminDashboardActivity.this, ManageDriverActivity.class);
                startActivity(intent);
            }
        });

        btnViewGarbageReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to View Garbage Report activity
                Intent intent = new Intent(AdminDashboardActivity.this, ViewGarbageReportActivity.class);
                startActivity(intent);
            }
        });

        btnViewComplaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to View Complaints activity
                Intent intent = new Intent(AdminDashboardActivity.this, ViewComplaintsActivity.class);
                startActivity(intent);
            }
        });
    }
}