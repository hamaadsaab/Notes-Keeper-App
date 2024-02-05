package com.example.appify.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appify.Customer;
import com.example.appify.Adapter.CustomerAdapter;
import com.example.appify.R;

import java.util.ArrayList;
import java.util.List;

public class RegistrationActivity extends AppCompatActivity {
    private List<Customer> customerList = new ArrayList<>();
    private CustomerAdapter customerAdapter; // Create a custom adapter for your RecyclerView

    Spinner spinner;
    String userRole;
    private String[] userRoleString = new String[] { "male", "female"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Initialize your RecyclerView and adapter
        View recyclerView = findViewById(R.id.recyclerView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            customerAdapter = new CustomerAdapter(customerList);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            recyclerView.setOnCapturedPointerListener(customerAdapter);
        }

        Button existingRegister = findViewById(R.id.existingText);
        existingRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });

        // Handle form submission (e.g., when a "Submit" button is clicked)
        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get user input from EditText fields
                EditText nameEditText = findViewById(R.id.fullname);
                EditText emailEditText = findViewById(R.id.email);
                EditText phoneEditText = findViewById(R.id.phone);
                EditText cityEditText = findViewById(R.id.city);
                EditText adressEditText = findViewById(R.id.address);

                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String phoneNumber = phoneEditText.getText().toString();
                String city = cityEditText.getText().toString();
                String address = adressEditText.getText().toString();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(phoneNumber) || TextUtils.isEmpty(city) || TextUtils.isEmpty(address)) {
                    // Display an error message or toast indicating empty fields
                    Toast.makeText(RegistrationActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Customer Registered !",Toast.LENGTH_SHORT).show();
                }
                // Create a Customer object
                Customer customer = new Customer(name, email, phoneNumber,city,address);

                // Add the customer to the list
                customerList.add(customer);

                // Notify the adapter that the data has changed
                customerAdapter.notifyDataSetChanged();

                // Clear the EditText fields
                nameEditText.setText("");
                emailEditText.setText("");
                phoneEditText.setText("");
                cityEditText.setText("");
                adressEditText.setText("");
            }
        });

    }
}

