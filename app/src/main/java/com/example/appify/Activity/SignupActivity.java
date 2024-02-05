package com.example.appify.Activity;

import android.app.Activity;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.appify.R;
import com.example.appify.databinding.ActivitySignup2Binding;

public class SignupActivity extends Activity {

    ActivitySignup2Binding binder;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binder= ActivitySignup2Binding.inflate(getLayoutInflater());
        setContentView(binder.getRoot());

// Create a SpannableString for the text
        String checkBoxText = "Agree to the Terms and Conditions";
        SpannableString spannableString = new SpannableString(checkBoxText);

// Find the start and end indices of the words you want to style
        int start = checkBoxText.indexOf("Terms");
        int end = start + "Terms and Conditions".length();

// Create a ClickableSpan for the underlined text
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // Show the terms and conditions dialog here
                showTermsAndConditionsDialog();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                // Customize the appearance of the underlined text
                ds.setUnderlineText(true);
                ds.setColor(Color.BLUE);
            }
        };

// Apply the ClickableSpan to the specified range
        spannableString.setSpan(clickableSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

// Set the styled text to the CheckBox
        binder.agreeTermsCheckbox.setText(spannableString);

// Enable the ClickableSpan to receive clicks
        binder.agreeTermsCheckbox.setMovementMethod(LinkMovementMethod.getInstance());

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        // Set up a click listener for the signup button
        binder.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get user input
                String username = binder.Username.getText().toString();
                String email = binder.inputEmail.getText().toString().trim();
                String password = binder.inputPassword.getText().toString();
                String confirmPassword = binder.inputConfirmPassword.getText().toString();
                boolean agreedToTerms = binder.agreeTermsCheckbox.isChecked();

                // Perform validation
                if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || !agreedToTerms) {
                    // Display a message to fill in all fields and agree to terms
                    Toast.makeText(SignupActivity.this, "Please fill in all fields and agree to the terms.", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(email)) {
                    // Display a message for an invalid email format
                    Toast.makeText(SignupActivity.this, "Please enter a valid email address.", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    // Display a message for password mismatch
                    Toast.makeText(SignupActivity.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                } else {
                    // Save user credentials to SharedPreferences
                    saveCredentials(username, password);

                    Intent intent= new Intent(SignupActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    private void saveCredentials(String username, String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.apply();
    }
    private boolean isValidEmail(String email) {
        // You can implement your email format validation here
        // This is a simple example; you can use regular expressions for a more robust check
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void onclicklogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void showTermsAndConditionsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Customize the appearance and content of the dialog
        View dialogView = getLayoutInflater().inflate(R.layout.custom_terms_dialog, null);
        TextView termsTextView = dialogView.findViewById(R.id.termsTextView);
        termsTextView.setText(R.string.terms);

        // Set up the AlertDialog
        builder.setTitle("Terms and Conditions");
        builder.setView(dialogView);

        // Set up positive button (OK)
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // User clicked "OK"
                dialog.dismiss();
            }
        });

        // Create and show the dialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
