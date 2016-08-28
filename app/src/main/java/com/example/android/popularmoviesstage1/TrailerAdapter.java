package com.example.android.popularmoviesstage1;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.media.CamcorderProfile.get;
import static android.widget.Toast.makeText;

/**
 * Created by lokesh on 22/7/16.
 */

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ViewHolder> {
    private List<Trailer.TrailerItem> items;


    public TrailerAdapter(List<Trailer.TrailerItem> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(itemView);

    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return -1;
        }
        return items.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Trailer.TrailerItem item = items.get(position);
        final String youtubeUrl = "https://www.youtube.com/watch?v=" + item.getKey();
        holder.textView.setText(item.getName());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent videoIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(youtubeUrl)
                );
                context.startActivity(videoIntent);
            }
        });

    }


    public void swapList(List<Trailer.TrailerItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    static final class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.trailer_url)
        TextView textView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}




