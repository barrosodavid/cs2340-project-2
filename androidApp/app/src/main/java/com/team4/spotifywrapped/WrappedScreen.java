package com.team4.spotifywrapped;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WrappedScreen extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.wrapped_screen);

    String top5Songs = getIntent().getStringExtra("top5Songs");
    String top5Artists = getIntent().getStringExtra("top5Artists");
    String top5Genres = getIntent().getStringExtra("top5Genres");
    String totalGenres = getIntent().getStringExtra("totalGenres");

    TextView textView = findViewById(R.id.textView);

    SpannableStringBuilder builder = new SpannableStringBuilder();

    // Bold and big for title These are your top 5 songs, artists, and genres from Spotify!
    SpannableString boldSpan =
        new SpannableString("These are your top 5 songs, artists, and genres from Spotify!\n\n");
    boldSpan.setSpan(
        new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, boldSpan.length(), 0);
    boldSpan.setSpan(new RelativeSizeSpan(3f), 0, boldSpan.length(), 0);
    // make Spotify light green
    boldSpan.setSpan(
        new android.text.style.ForegroundColorSpan(Color.GREEN),
        boldSpan.toString().indexOf("Spotify"),
        boldSpan.toString().indexOf("Spotify") + "Spotify".length(),
        0);
    // make top 5 purple
    boldSpan.setSpan(
        new android.text.style.ForegroundColorSpan(Color.MAGENTA),
        boldSpan.toString().indexOf("top 5"),
        boldSpan.toString().indexOf("top 5") + "top 5".length(),
        0);
    // make songs orange
    boldSpan.setSpan(
        new android.text.style.ForegroundColorSpan(Color.rgb(255, 165, 0)),
        boldSpan.toString().indexOf("songs"),
        boldSpan.toString().indexOf("songs") + "songs".length(),
        0);
    // make artists red
    boldSpan.setSpan(
        new android.text.style.ForegroundColorSpan(Color.RED),
        boldSpan.toString().indexOf("artists"),
        boldSpan.toString().indexOf("artists") + "artists".length(),
        0);
    // make genres blue
    boldSpan.setSpan(
        new android.text.style.ForegroundColorSpan(Color.CYAN),
        boldSpan.toString().indexOf("genres"),
        boldSpan.toString().indexOf("genres") + "genres".length(),
        0);

    builder.append(boldSpan);

    // Subtitle Top 5 Songs:
    SpannableString subtitle = new SpannableString("Top 5 Songs:\n");
    subtitle.setSpan(
        new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, subtitle.length(), 0);
    subtitle.setSpan(new RelativeSizeSpan(2f), 0, subtitle.length(), 0);
    // make it orange
    subtitle.setSpan(
        new android.text.style.ForegroundColorSpan(Color.rgb(255, 165, 0)),
        0,
        subtitle.length(),
        0);
    builder.append(subtitle);

    // Top 5 Songs
    builder.append(top5Songs + "\n\n");

    // Subtitle Top 5 Artists:
    subtitle = new SpannableString("Top 5 Artists:\n");
    subtitle.setSpan(
        new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, subtitle.length(), 0);
    subtitle.setSpan(new RelativeSizeSpan(2f), 0, subtitle.length(), 0);
    // make it red
    subtitle.setSpan(
        new android.text.style.ForegroundColorSpan(Color.RED), 0, subtitle.length(), 0);
    builder.append(subtitle);

    // Top 5 Artists
    builder.append(top5Artists + "\n\n");

    // Subtitle Total Genres:
    subtitle = new SpannableString("Total Genres:\n");
    subtitle.setSpan(
        new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, subtitle.length(), 0);
    subtitle.setSpan(new RelativeSizeSpan(2f), 0, subtitle.length(), 0);
    // make it blue
    subtitle.setSpan(
        new android.text.style.ForegroundColorSpan(Color.CYAN), 0, subtitle.length(), 0);
    builder.append(subtitle);

    // Total Genres
    SpannableString number = new SpannableString(totalGenres);
    number.setSpan(
        new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, number.length(), 0);
    number.setSpan(new RelativeSizeSpan(3f), 0, number.length(), 0);
    builder.append(number);

    // Subtitle Top 5 Genres:
    subtitle = new SpannableString("\nTop 5 Genres:\n");
    subtitle.setSpan(
        new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, subtitle.length(), 0);
    subtitle.setSpan(new RelativeSizeSpan(2f), 0, subtitle.length(), 0);
    // make it green
    subtitle.setSpan(
        new android.text.style.ForegroundColorSpan(Color.WHITE), 0, subtitle.length(), 0);
    builder.append(subtitle);

    // Top 5 Genres
    builder.append(top5Genres + "\n\n");

    // Set the text
    textView.setText(builder);
  }
}
