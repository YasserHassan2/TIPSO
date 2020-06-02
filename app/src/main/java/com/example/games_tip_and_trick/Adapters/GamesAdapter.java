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
import com.example.games_tip_and_trick.R;

import java.util.List;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.RecyclerViewHolders>{

    private List<Game> GameList;
    private Context context;
    CustomItemClickListener listener;
    public GamesAdapter(Context context, List<Game> GameList) {
        this.GameList = GameList;
        this.context = context;
    }

    @Override
    public GamesAdapter.RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_row, parent,false);
        final GamesAdapter.RecyclerViewHolders holder = new GamesAdapter.RecyclerViewHolders(layoutView);
        layoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, holder.getPosition());

            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(GamesAdapter.RecyclerViewHolders holder, int position) {

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.loading);

        Glide.with(context)
                .load(GameList.get(position).getImageURL())
                .apply(requestOptions)
                .into(holder.GameImage);

        holder.GameName.setText(GameList.get(position).getTitle());

    }


    //Set method of OnItemClickListener object
    public void setOnItemClickListener(CustomItemClickListener recyclerViewItemClickListner) {
        this.listener = recyclerViewItemClickListner;
    }


    @Override
    public int getItemCount() {
        return this.GameList.size();
    }

    public class RecyclerViewHolders extends RecyclerView.ViewHolder {

        /**
         * this class contains onclick listener for the recylcer view home
         */

        public TextView GameName;
        public ImageView GameImage;
        public int position = 0;

        public RecyclerViewHolders(View itemView) {
            super(itemView);
            GameName = (TextView) itemView.findViewById(R.id.game_name);
             GameImage = (ImageView) itemView.findViewById(R.id.iv_game_image);


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