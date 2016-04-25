package com.example.jasdipc.mafiapartygame;

import android.content.Context;

import com.google.android.gms.common.api.GoogleApiClient;

public class NearbyClient {

    private static NearbyClient nearbyClient;
    private Context mContext;
    private GoogleApiClient mGoogelApiClient;

    public static NearbyClient getInstance(Context mContext) {
        if (nearbyClient == null) {
            nearbyClient = new NearbyClient(mContext);
        }
        return nearbyClient;
    }

    private NearbyClient(Context mContext) {
        this.mContext = mContext;
    }
}
