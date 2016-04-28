package com.example.jasdipc.mafiapartygame.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jasdipc.mafiapartygame.*;

public class ListViewRowHolder extends RecyclerView.ViewHolder {

    protected TextView player_name;
    protected Button invite_player_button;

    public ListViewRowHolder(final View itemView) {
        super(itemView);

        player_name = (TextView) itemView.findViewById(R.id.player_name);
        invite_player_button = (Button) itemView.findViewById(R.id.invite_player_button);
    }
}
