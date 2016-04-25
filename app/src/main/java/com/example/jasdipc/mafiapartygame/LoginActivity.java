package com.example.jasdipc.mafiapartygame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    private NearbyHost nearbyHost;
    private NearbyClient nearbyClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nearbyHost = NearbyHost.getInstance(LoginActivity.this);
        nearbyClient = NearbyClient.getInstance(LoginActivity.this);
    }
}
