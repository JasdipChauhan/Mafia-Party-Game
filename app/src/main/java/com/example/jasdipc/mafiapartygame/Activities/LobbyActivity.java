package com.example.jasdipc.mafiapartygame.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.jasdipc.mafiapartygame.Adapters.LobbyAdapter;
import com.example.jasdipc.mafiapartygame.Callbacks.NewMemberInterface;
import com.example.jasdipc.mafiapartygame.Models.Member;
import com.example.jasdipc.mafiapartygame.R;
import com.example.jasdipc.mafiapartygame.Singletons.ApiClient;
import com.example.jasdipc.mafiapartygame.Singletons.NearbyClient;
import com.example.jasdipc.mafiapartygame.Singletons.NearbyHost;

import java.util.ArrayList;
import java.util.List;

public class LobbyActivity extends AppCompatActivity implements NewMemberInterface{

    private RecyclerView rv;
    private List<Member> clientMembers = new ArrayList<>();
    private LobbyAdapter adapter;
    private boolean isHost;
    private NearbyHost nearbyHost;
    private NearbyClient nearbyClient;
    private ApiClient apiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        apiClient = ApiClient.getApiClientInstance(LobbyActivity.this);
        rv = (RecyclerView) findViewById(R.id.rv);
        isHost = getIntent().getBooleanExtra("isHost", false);

        LinearLayoutManager llm = new LinearLayoutManager(LobbyActivity.this);
        rv.setLayoutManager(llm);

        adapter =  new LobbyAdapter(clientMembers, LobbyActivity.this);
        rv.setAdapter(adapter);

        if (isHost) {
            initHost();
        } else {
            initPlayer();
        }
    }

    private void initHost() {
        nearbyHost = NearbyHost.getInstance(LobbyActivity.this);
        nearbyHost.advertise("");
    }

    private void initPlayer() {
        nearbyClient = NearbyClient.getInstance(LobbyActivity.this, this);
        nearbyClient.discover();
    }

    @Override
    protected void onStart() {
        super.onStart();
        apiClient = ApiClient.getApiClientInstance(LobbyActivity.this);
        apiClient.connect();
    }

    @Override
    public void foundNewMember(String name, String endpointID) {
        adapter.add(new Member(name, endpointID));
        adapter.notifyDataSetChanged();
    }
}
