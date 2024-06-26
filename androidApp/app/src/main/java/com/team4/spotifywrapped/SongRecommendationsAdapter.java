package com.team4.spotifywrapped;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SongRecommendationsAdapter
    extends RecyclerView.Adapter<SongRecommendationsAdapter.ViewHolder> {

  private final List<SongRecommendation> songList;
  private final Context context;

  // Constructor
  public SongRecommendationsAdapter(Context context, List<SongRecommendation> songList) {
    this.context = context;
    this.songList = songList;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_song_recommendation, parent, false);
    return new ViewHolder(view);
  }

  @SuppressLint("SetTextI18n")
  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    SongRecommendation song = songList.get(position);
    holder.songNameText.setText(song.getName() + " - " + song.getArtistName());
    holder.genreText.setText("Genre: " + song.getGenre());
    // Assuming you have Picasso or Glide added to your project
    Picasso.get().load(song.getImageUrl()).into(holder.imageView);
  }

  @Override
  public int getItemCount() {
    return songList.size();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    public TextView songNameText, genreText;
    public ImageView imageView;

    public ViewHolder(View view) {
      super(view);
      songNameText = view.findViewById(R.id.song_name);
      genreText = view.findViewById(R.id.genre);
      imageView = view.findViewById(R.id.image);
    }
  }
}
