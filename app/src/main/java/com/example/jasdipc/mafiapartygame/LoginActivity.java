package com.example.jasdipc.mafiapartygame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    private NearbyHost nearbyHost;
    private NearbyClient nearbyClient;
    private ApiClient apiClient;
    private NetworkConnectivity networkConnectivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //nearbyHost = NearbyHost.getInstance(LoginActivity.this);
        //nearbyClient = NearbyClient.getInstance(LoginActivity.this);


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

    private void initNetworkVerification() {
        networkConnectivity = new NetworkConnectivity(LoginActivity.this);
        if (!networkConnectivity.isConnectedToNetwork()) {
            //create an alert dialog
        }
    }


}
