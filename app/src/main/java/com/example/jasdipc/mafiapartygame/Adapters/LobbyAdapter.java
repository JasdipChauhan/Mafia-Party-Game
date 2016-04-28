package com.example.jasdipc.mafiapartygame.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.jasdipc.mafiapartygame.Models.Member;
import com.example.jasdipc.mafiapartygame.R;

import java.util.ArrayList;
import java.util.List;

public class LobbyAdapter extends RecyclerView.Adapter<ListViewRowHolder> {

    private List<Member> membersList = new ArrayList<>();
    private int lastPosition = -1;
    private Context mContext;

    public LobbyAdapter(List<Member> membersList, Context mContext) {
        this.membersList = membersList;
        this.mContext = mContext;
    }

    @Override
    public ListViewRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.host_lobby_card, null);
        ListViewRowHolder holder = new ListViewRowHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ListViewRowHolder holder, int position) {
        Member member = membersList.get(position);

        holder.player_name.setText(member.getName());

        setAnimation(holder.itemView, position);
        holder.getLayoutPosition();
    }

    @Override
    public int getItemCount() {
        return (null != membersList ? membersList.size() : 0);
    }

    public void add(Member member) {
        membersList.add(member);
        notifyItemInserted(membersList.size()-1);
    }

    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}
