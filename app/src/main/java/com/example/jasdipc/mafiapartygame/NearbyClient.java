package com.example.jasdipc.mafiapartygame;

import android.content.Context;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.nearby.connection.Connections;

public class NearbyClient implements Connections.MessageListener,
        Connections.EndpointDiscoveryListener{

    private static NearbyClient nearbyClient;
    private Context mContext;
    private ApiClient apiClient;

    public static NearbyClient getInstance(Context mContext) {
        if (nearbyClient == null) {
            nearbyClient = new NearbyClient(mContext);
        }
        return nearbyClient;
    }

    private NearbyClient(Context mContext) {
        this.mContext = mContext;
        apiClient = ApiClient.getApiClientInstance(mContext);
        apiClient.connect();
    }

    @Override
    public void onEndpointFound(String s, String s1, String s2, String s3) {

    }

    @Override
    public void onEndpointLost(String s) {

    }

    @Override
    public void onMessageReceived(String s, byte[] bytes, boolean b) {

    }

    @Override
    public void onDisconnected(String s) {

    }
}
