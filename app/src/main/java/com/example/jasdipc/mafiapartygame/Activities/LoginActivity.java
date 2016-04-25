package com.example.jasdipc.mafiapartygame.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jasdipc.mafiapartygame.R;
import com.example.jasdipc.mafiapartygame.Singletons.*;
import com.example.jasdipc.mafiapartygame.Verification.*;

public class LoginActivity extends AppCompatActivity {

    private NearbyHost nearbyHost;
    private NearbyClient nearbyClient;
    private ApiClient apiClient;
    private NetworkConnectivity networkConnectivity;

    private Button host_button;
    private Button client_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initNetworkVerification();
        initReferences();
        initOnClickListeners();

        //nearbyHost = NearbyHost.getInstance(LoginActivity.this);
        //nearbyClient = NearbyClient.getInstance(LoginActivity.this);
    }

    private void initOnClickListeners() {

        final Intent i = new Intent(LoginActivity.this, LobbyActivity.class);

        host_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nearbyHost = NearbyHost.getInstance(LoginActivity.this);
                i.putExtra("status", "host");
                finish();
                startActivity(i);
            }
        });

        client_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nearbyClient = NearbyClient.getInstance(LoginActivity.this);
                i.putExtra("status", "client");
                finish();
                startActivity(i);
            }
        });
    }

    private void initReferences() {
        host_button = (Button) findViewById(R.id.host_button);
        client_button = (Button) findViewById(R.id.client_button);
    }

    private void initNetworkVerification() {
        networkConnectivity = new NetworkConnectivity(LoginActivity.this);
        if (!networkConnectivity.isConnectedToNetwork()) {
            //create an alert dialog
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        apiClient.disconnect();
    }

    @Override
    protected void onStart() {
        super.onStart();
        apiClient = ApiClient.getApiClientInstance(LoginActivity.this);
        apiClient.connect();
    }

}