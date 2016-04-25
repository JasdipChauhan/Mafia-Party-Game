package com.example.jasdipc.mafiapartygame;

import android.content.Context;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.connection.Connections;

public class NearbyHost implements Connections.MessageListener,
        Connections.ConnectionRequestListener{

    private static NearbyHost nearbyHost;
    private Context mContext;
    private GoogleApiClient mGoogleApiClient;

    private boolean isConnected;

    public static NearbyHost getInstance(Context mContext) {
        if (nearbyHost == null) {
            nearbyHost = new NearbyHost(mContext);
        }
        return nearbyHost;
    }

    private NearbyHost(Context mContext) {
        this.mContext = mContext;
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
