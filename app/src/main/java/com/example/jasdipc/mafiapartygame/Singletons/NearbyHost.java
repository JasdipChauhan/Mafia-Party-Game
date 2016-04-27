package com.example.jasdipc.mafiapartygame.Singletons;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.jasdipc.mafiapartygame.R;
import com.example.jasdipc.mafiapartygame.Verification.NetworkConnectivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.connection.Connections;
import com.google.android.gms.nearby.connection.ConnectionsStatusCodes;

public class NearbyHost implements Connections.MessageListener,
        Connections.ConnectionRequestListener{

    private static NearbyHost nearbyHost;
    private static Context mContext;
    private ApiClient apiClient;

    public static NearbyHost getInstance(Context mContext) {
        if (nearbyHost == null) {
            nearbyHost = new NearbyHost(mContext);
        }
        NearbyHost.mContext = mContext;
        return nearbyHost;
    }

    private NearbyHost(Context mContext) {
        this.mContext = mContext;
        apiClient = ApiClient.getApiClientInstance(mContext);
        apiClient.connect();
    }

    public void advertise(String lobbyName) {

        if (apiClient.getmGoogleApiClient().isConnected()) {
            Log.i("advertising", "google api connected");

        }

        Nearby.Connections.startAdvertising(apiClient.getmGoogleApiClient(), "advertising", null, 1000L, this).setResultCallback(new ResultCallback<Connections.StartAdvertisingResult>() {
            @Override
            public void onResult(Connections.StartAdvertisingResult result) {

                Log.i("advertising", "got result");

                if (result.getStatus().isSuccess()) {
                    //Snackbar.make(view, "Finding Players", Snackbar.LENGTH_SHORT).show();
                    Toast.makeText(mContext, "Finding Players", Toast.LENGTH_SHORT).show();
                    Log.i("advertising", "working");
                } else {
                    int status = result.getStatus().getStatusCode();
                    Log.i("advertising", "failed");
                    if (status == ConnectionsStatusCodes.STATUS_ALREADY_ADVERTISING) {
                        Nearby.Connections.stopAdvertising(apiClient.getmGoogleApiClient());
                        //Snackbar.make(view, "Stopped looking for players", Snackbar.LENGTH_SHORT).show();
                    } else {
                        //Snackbar.make(view, "Failed to look for players...", Snackbar.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onConnectionRequest(String s, String s1, String s2, byte[] bytes) {

    }

    @Override
    public void onMessageReceived(String s, byte[] bytes, boolean b) {

    }

    @Override
    public void onDisconnected(String s) {

    }


}
