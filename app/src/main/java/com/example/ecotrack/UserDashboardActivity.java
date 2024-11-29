package com.example.ecotrack;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ecotrack.userActivity.MyComplaintsActivity;
import com.example.ecotrack.userActivity.RegisterComplaintActivity;
import com.example.ecotrack.userActivity.UpdateProfileActivity;

public class UserDashboardActivity extends AppCompatActivity {

    private Button btnRegisterComplaint, btnMyComplaints, btnUpdateProfile;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        // Initialize Buttons
        btnRegisterComplaint = findViewById(R.id.btn_register_complaint);
        btnMyComplaints = findViewById(R.id.btn_my_complaints);
        btnUpdateProfile = findViewById(R.id.btn_update_profile);

        // Set Click Listeners
        btnRegisterComplaint.setOnClickListener(v -> {
            // Open Register Complaint Activity
            Intent intent = new Intent(UserDashboardActivity.this, RegisterComplaintActivity.class);
            startActivity(intent);
        });

        btnMyComplaints.setOnClickListener(v -> {
            // Open My Complaints Activity
            Intent intent = new Intent(UserDashboardActivity.this, MyComplaintsActivity.class);
            startActivity(intent);
        });

        btnUpdateProfile.setOnClickListener(v -> {
            // Open Update Profile Activity
            Intent intent = new Intent(UserDashboardActivity.this, UpdateProfileActivity.class);
            startActivity(intent);
        });
    }
}