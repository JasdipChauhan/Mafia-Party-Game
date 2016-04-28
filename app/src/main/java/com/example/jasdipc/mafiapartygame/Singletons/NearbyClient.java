package com.example.jasdipc.mafiapartygame.Singletons;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.jasdipc.mafiapartygame.Callbacks.*;
import com.example.jasdipc.mafiapartygame.Models.*;
import com.example.jasdipc.mafiapartygame.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.connection.Connections;


public class NearbyClient implements Connections.MessageListener,
        Connections.EndpointDiscoveryListener{

    private static NearbyClient nearbyClient;
    private static Context mContext;
    private ApiClient apiClient;
    public static NewMemberInterface newMemberEvent;

    public static NearbyClient getInstance(Context mContext, NewMemberInterface newMemberEvent) {
        if (nearbyClient == null) {
            nearbyClient = new NearbyClient(mContext, newMemberEvent);
        }
        NearbyClient.mContext = mContext;
        NearbyClient.newMemberEvent = newMemberEvent;
        return nearbyClient;
    }

    private NearbyClient(Context mContext, NewMemberInterface newMemberEvent) {
        this.mContext = mContext;
        this.newMemberEvent = newMemberEvent;
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
    public void onEndpointFound(String endpointID, String deviceID, String serviceID, String name) {
        Toast.makeText(mContext, name, Toast.LENGTH_SHORT).show();
        Toast.makeText(mContext, endpointID, Toast.LENGTH_SHORT).show();
        newMemberEvent.foundNewMember(name, endpointID);
    }

    @Override
    public void onEndpointLost(String endpointID) {

    }

    @Override
    public void onMessageReceived(String remoteEndpointID, byte[] payload, boolean isReliable) {

    }

    @Override
    public void onDisconnected(String remoteEndpointID) {

    }
}
