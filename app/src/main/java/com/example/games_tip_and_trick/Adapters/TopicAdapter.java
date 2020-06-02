package com.example.games_tip_and_trick.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.games_tip_and_trick.Models.Game;
import com.example.games_tip_and_trick.Models.Topic;
import com.example.games_tip_and_trick.R;

import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.RecyclerViewHolders>{

    private List<Topic> topicList;
    private Context context;
    CustomItemClickListener listener;
    public TopicAdapter(Context context, List<Topic> topicList) {
        this.topicList = topicList;
        this.context = context;
    }

    @Override
    public TopicAdapter.RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.topic_row, parent,false);
        final TopicAdapter.RecyclerViewHolders holder = new TopicAdapter.RecyclerViewHolders(layoutView);
        layoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, holder.getPosition());

            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(TopicAdapter.RecyclerViewHolders holder, int position) {



        holder.topic_title.setText(topicList.get(position).getTitle());

    }


    //Set method of OnItemClickListener object
    public void setOnItemClickListener(CustomItemClickListener recyclerViewItemClickListner) {
        this.listener = recyclerViewItemClickListner;
    }
    public void setData(List<Topic> list) {
        topicList = list;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return this.topicList.size();
    }

    public class RecyclerViewHolders extends RecyclerView.ViewHolder {

        /**
         * this class contains onclick listener for the recylcer view home
         */

        public TextView topic_title;
        public int position = 0;

        public RecyclerViewHolders(View itemView) {
            super(itemView);
            topic_title = (TextView) itemView.findViewById(R.id.topic_title);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //When item view is clicked, trigger the itemclicklistener
                    //Because that itemclicklistener is indicated in MainActivity
                    listener.onItemClick(v, position);
                }
            });
        }


    }


}