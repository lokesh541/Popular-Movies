package com.example.android.popularmoviesstage1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lokesh on 5/8/16.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private List<Review.ReviewItem> items;


    public ReviewAdapter(List<Review.ReviewItem> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_review, parent, false);
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
        final Review.ReviewItem review = items.get(position);

        holder.content.setText(review.getContent());
        holder.author.setText(review.getAuthor());

    }


    public void swapList(List<Review.ReviewItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    static final class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.review_content)
        TextView content;
        @BindView(R.id.review_author)
        TextView author;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
