package com.example.jasdipc.mafiapartygame.Activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.jasdipc.mafiapartygame.R;
import com.example.jasdipc.mafiapartygame.Singletons.*;
import com.example.jasdipc.mafiapartygame.Verification.*;
import com.google.android.gms.nearby.Nearby;

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
        apiClient = ApiClient.getApiClientInstance(LoginActivity.this);

        initNetworkVerification();
        initReferences();
        initOnClickListeners();
    }

    private void initOnClickListeners() {

        final Intent i = new Intent(LoginActivity.this, LobbyActivity.class);

        host_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nearbyHost = NearbyHost.getInstance(LoginActivity.this);
                i.putExtra("isHost", true);
                finish();
                startActivity(i);
            }
        });

        client_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nearbyClient = NearbyClient.getInstance(LoginActivity.this);
                i.putExtra("isHost", false);
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
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setMessage("You are not connected to the network, please connect to the WIFI network")
                    .setPositiveButton("Okay", null);
            builder.create();
            builder.show();
        }
    }

   /*@Override
    protected void onStop() {
        super.onStop();
        apiClient = ApiClient.getApiClientInstance(LoginActivity.this);
        apiClient.disconnect();
        Log.i("api login activity", "disconnecting");
    }*/

    @Override
    protected void onStart() {
        super.onStart();
        apiClient = ApiClient.getApiClientInstance(LoginActivity.this);
        apiClient.connect();
        Log.i("api login activity", "connecting");
    }
}
