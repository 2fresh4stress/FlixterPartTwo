package com.codepath.rkpandey.flixter2.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.rkpandey.flixter2.R;
import com.codepath.rkpandey.flixter2.models.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.Viewholder> {

    Context context;
    List<Movie> movies;

public MovieAdapter (Context context, List<Movie> movies) {
    this.context = context;
    this.movies = movies;
}

// Usually involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.d("MovieAdapter", "onCreateViewHolder");
    View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
    return new Viewholder(movieView);
    }

// Involves populating data into the item through the holder
     @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
    Log.d ("MovieAdapter", "onBlindViewHolder" + position);
//Get the movie at the passed in position
        Movie movie = movies.get(position);
        //Bind the movie data into the VH
        holder.bind(movie);
    }

    // Return the total count of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
        }

        public void bind(final Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            String imageUrl;
            // if phone in landscape then imageUrl = back drop image
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                imageUrl = movie.getBackdropPath();
            } else{
                // else imageUrl = poster image
                imageUrl = movie.getPosterPath();
            }
            Glide.with(context).load(imageUrl).into(ivPoster);
        }
    }

}

