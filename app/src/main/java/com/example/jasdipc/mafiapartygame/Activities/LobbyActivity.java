package com.example.jasdipc.mafiapartygame.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.jasdipc.mafiapartygame.Adapters.LobbyAdapter;
import com.example.jasdipc.mafiapartygame.Models.ClientMember;
import com.example.jasdipc.mafiapartygame.R;

import java.util.ArrayList;
import java.util.List;

public class LobbyActivity extends AppCompatActivity {

    private RecyclerView rv;
    private List<ClientMember> clientMembers = new ArrayList<>();
    private LobbyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        rv = (RecyclerView) findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(LobbyActivity.this);
        rv.setLayoutManager(llm);

        adapter =  new LobbyAdapter();
        rv.setAdapter(adapter);
    }
}
