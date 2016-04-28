package com.example.jasdipc.mafiapartygame.Singletons;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.jasdipc.mafiapartygame.Callbacks.*;
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
    public static MemberDiscoveryInterface memberDiscoveryInterface;

    public static NearbyClient getInstance(Context mContext, MemberDiscoveryInterface newMemberEvent) {
        if (nearbyClient == null) {
            nearbyClient = new NearbyClient(mContext, newMemberEvent);
        }
        NearbyClient.mContext = mContext;
        NearbyClient.memberDiscoveryInterface = newMemberEvent;
        return nearbyClient;
    }

    private NearbyClient(Context mContext, MemberDiscoveryInterface newMemberEvent) {
        this.mContext = mContext;
        this.memberDiscoveryInterface = newMemberEvent;
        apiClient = ApiClient.getApiClientInstance(mContext);
        apiClient.connect();
    }

    public void discover() {

        String serviceID = mContext.getString(R.string.service_id);

        Nearby.Connections.startDiscovery(apiClient.getmGoogleApiClient(), serviceID, 0L, this).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(Status status) {
                if (status.isSuccess()) {
                    Log.i("discovering", "working");
                } else {
                    Log.i("discovering", "failed");
                }
            }
        });

    }

    @Override
    public void onEndpointFound(String endpointID, String deviceID, String serviceID, String name) {
        memberDiscoveryInterface.foundNewMember(name, endpointID);
    }

    @Override
    public void onEndpointLost(String endpointID) {
        memberDiscoveryInterface.lostMember(endpointID);
    }

    public void sendRequest(final String endpointID, String usersName) {

        Nearby.Connections.sendConnectionRequest(apiClient.getmGoogleApiClient(), "CLIENT NAME", endpointID, null, new Connections.ConnectionResponseCallback() {
            @Override
            public void onConnectionResponse(String s, Status status, byte[] bytes) {

            }
        }, this);

    }

    @Override
    public void onMessageReceived(String remoteEndpointID, byte[] payload, boolean isReliable) {

    }

    @Override
    public void onDisconnected(String remoteEndpointID) {

    }
}
