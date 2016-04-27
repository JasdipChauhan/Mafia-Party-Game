package com.example.jasdipc.mafiapartygame.Singletons;

import android.content.Context;
import android.util.Log;

import com.example.jasdipc.mafiapartygame.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.connection.Connections;

import java.util.PriorityQueue;

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

    public void discover() {

        String serviceID = mContext.getString(R.string.service_id);

        Nearby.Connections.startDiscovery(apiClient.getmGoogleApiClient(), serviceID, 0L, this).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(Status status) {
                if(status.isSuccess()) {
                    Log.i("discovering", "working");
                } else {
                    Log.i("discovering", "failed");
                }
            }
        });

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
