package com.example.appify.Activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentTransaction;

import com.example.appify.API.GET.Display_API;
import com.example.appify.API.POST.Display_POST;
import com.example.appify.Fragments.DynamicFragment;
import com.example.appify.Fragments.StaticFragment;
import com.example.appify.R;
import com.example.appify.recylerview.form.form;

public class HomeActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "Appify_Channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnStaticFragment = findViewById(R.id.showStatic);
        Button btnApiGet = findViewById(R.id.APIGetButton);
        Button btnApiResponse = findViewById(R.id.APIPostButton);

        btnStaticFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a fragment transaction
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                // Replace the current fragment with the static fragment
                transaction.replace(R.id.fragmentContainer, new StaticFragment());

                // Add the transaction to the back stack
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();
            }
        });

        btnApiGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, Display_API.class);
                startActivity(intent);
            }
        });

        btnApiResponse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, Display_POST.class);
                startActivity(intent);
            }
        });

        createNotificationChannel();

        Button showNotificationButton = findViewById(R.id.show_notification_button);
        showNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotification();
            }
        });
    }

    public void onclickCalculator(View view) {
        Intent intent = new Intent(this, CalculatorActivity.class);
        startActivity(intent);
    }

    public void onclickForm(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    public void onclickView(View view) {
        Intent intent = new Intent(this, RecyclerActivity.class);
        startActivity(intent);
    }
    public void onclickCustomerDb(View view) {
        Intent intent = new Intent(this, form.class);
        startActivity(intent);
    }

    public void onclickDFragment(View view) {
        DynamicFragment settingsFragment = new DynamicFragment();

        // Add the fragment to the container using a FragmentTransaction
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, settingsFragment)
                .addToBackStack(null)  // Add to the back stack for navigation
                .commit();
    }

    public void onclickStaticFragment(View view) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, new StaticFragment())
                .addToBackStack(null)
                .commit();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Appify";
            String description = "Notes App";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void showNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Pending Assessment")
                .setContentText("MAD Project")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());
    }
}