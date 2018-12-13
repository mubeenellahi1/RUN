package com.example.mobeen.run;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.MyViewHolder> {

    private List<Feed> feedList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, author, date,content;
        public CardView parentLayout;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.fTitleTV);
            author = (TextView) view.findViewById(R.id.fAuthorTV);
            date = (TextView) view.findViewById(R.id.fDateTV);
            content = (TextView) view.findViewById(R.id.fContentTV);
            parentLayout=(CardView)view.findViewById(R.id.msgCardView);
        }
    }

    public FeedAdapter(List<Feed> feedList,Context context) {
        this.feedList = feedList;
        this.context=context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Feed feed  = feedList.get(position);
        holder.title.setText((feed.getTitle()));
        holder.author.setText(feed.getAuthor());
        holder.date.setText(feed.getDate());
        holder.content.setText(feed.getContent());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,BlogActivity.class);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }
}
