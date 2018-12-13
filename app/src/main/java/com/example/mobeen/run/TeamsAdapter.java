package com.example.mobeen.run;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobeen.run.Models.User;

import java.util.List;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.MyViewHolder> {

    private List<User> teamsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, level, age;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.tNameTV);
            level = (TextView) view.findViewById(R.id.tLevelTV);
            age = (TextView) view.findViewById(R.id.avgAgeTV);
        }
    }


    public TeamsAdapter(List<User> userList) {
        this.teamsList = userList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.team_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        User user = teamsList.get(position);
        holder.name.setText((user.getTeamName()));
        holder.age.setText("Average Age: 23");
        holder.level.setText("Level:0");
    }

    @Override
    public int getItemCount() {
        return teamsList.size();
    }
}