package com.example.jasdipc.mafiapartygame;

import android.content.Context;

public class NearbyClient {

    private static NearbyClient nearbyClient;
    private Context mContext;

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
