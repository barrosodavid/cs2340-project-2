package com.team4.spotifywrapped;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;





public class GamesFragment extends Fragment {

  @Nullable
  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_games, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    Button gameBtn = view.findViewById(R.id.game_btn);
    Button game2Btn = view.findViewById(R.id.game2_btn);

    gameBtn.setOnClickListener(
        v -> {
          try {
            Toast.makeText(getContext(), "Playing game, this may take a while", Toast.LENGTH_SHORT)
                .show();
            play_game();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        });

    game2Btn.setOnClickListener(
        v -> {
          // playgame2();
        });
  }

  private void play_game() throws InterruptedException {}
  /*
      getPlaylists();
      sleep(2000);

      // Get all the songs from the playlists
      for (Map.Entry<String, Pair<String, String>> entry : playlists.entrySet()) {
          // Use id to get the songs
          spotifyRequest_playlist_songs(entry.getValue().first);
      }
      sleep(3000);
      // Choose a random playlist and a random song from that playlist
      Random rand = new Random();
      int randomPlaylistIndex = rand.nextInt(playlists.size());
      String randomPlaylist = (String) playlists.keySet().toArray()[randomPlaylistIndex];
      String randomPlaylistId = playlists.get(randomPlaylist).first;
      ArrayList<String> songs = playlist_songs.get(randomPlaylistId);
      int randomSongIndex = rand.nextInt(songs.size());
      String randomSong = songs.get(randomSongIndex);

      // Choose 2 more playlists that don't contain the random song that was chosen
      ArrayList<String> playlists_without_song = new ArrayList<>();
      for (Map.Entry<String, Pair<String, String>> entry : playlists.entrySet()) {
          if (!playlist_songs.get(entry.getValue().first).contains(randomSong)) {
              playlists_without_song.add(entry.getKey());
          }
      }
      // print the song, the playlist and the other 2 playlists
      String txt =
              "Song: "
                      + randomSong
                      + "(Original Playlist: "
                      + randomPlaylist
                      + ")\nChoose the playlist: ";
      // Print 3 playlists, randomize in which order the original playlist is shown
      // It can either be the first, second or third
      int randomOrder = rand.nextInt(3);
      if (randomOrder == 0) {
          txt += randomPlaylist + "\n";
          // Choose randomly another playlist from the ones that don't contain the song
          String playlist1 = playlists_without_song.get(rand.nextInt(playlists_without_song.size()));
          txt += playlist1 + "\n";
          // Delete the playlist that was chosen from the list
          playlists_without_song.remove(playlist1);
          txt += playlists_without_song.get(rand.nextInt(playlists_without_song.size())) + "\n";
      } else if (randomOrder == 1) {
          String playlist1 = playlists_without_song.get(rand.nextInt(playlists_without_song.size()));
          txt += playlist1 + "\n";
          playlists_without_song.remove(playlist1);
          txt += randomPlaylist + "\n";
          txt += playlists_without_song.get(rand.nextInt(playlists_without_song.size())) + "\n";
      } else {
          String playlist1 = playlists_without_song.get(rand.nextInt(playlists_without_song.size()));
          txt += playlist1 + "\n";
          playlists_without_song.remove(playlist1);
          txt += playlists_without_song.get(rand.nextInt(playlists_without_song.size())) + "\n";
          txt += randomPlaylist + "\n";
      }

      String finalTxt = txt;
      getActivity().runOnUiThread(() -> profileTextView.setText(finalTxt));
  }

  public void getPlaylists() {
      String url = "https://api.spotify.com/v1/me/playlists";
      spotifyRequest_playlist(url);
  }

  public void spotifyRequest_playlist(String url_parameter) {
      if (mAccessToken == null) {
          if (getContext() != null) {
              Toast.makeText(getContext(), "You need to get an access token first!", Toast.LENGTH_SHORT).show();
          }
          return;
      }

      // Create a request to get the user profile
      final Request request =
              new Request.Builder()
                      .url(url_parameter)
                      .addHeader("Authorization", "Bearer " + mAccessToken)
                      .build();

      cancelCall();
      mCall = mOkHttpClient.newCall(request);

      mCall.enqueue(
              new Callback() {
                  @Override
                  public void onFailure(Call call, IOException e) {
                      Log.d("HTTP", "Failed to fetch data: " + e);
                      if (getContext() != null) {
                          Toast.makeText(getContext(), "Failed to fetch data, watch Logcat for more details", Toast.LENGTH_SHORT).show();
                      }
                  }

                  @Override
                  public void onResponse(Call call, Response response) throws IOException {
                      try {
                          final JSONObject jsonObject = new JSONObject(response.body().string());
                          save_playlist(jsonObject, profileTextView);
                      } catch (JSONException e) {
                          Log.d("JSON", "Failed to parse data: " + e);
                          if (getContext() != null) {
                              Toast.makeText(getContext(), "Failed to fetch data, watch Logcat for more details", Toast.LENGTH_SHORT).show();
                          }
                      }
                  }
              });
  }

  // Get user profile This method will get the user profile using the token
  public void onGetUserProfileClicked() {
      String url = "https://api.spotify.com/v1/me";
      spotifyRequest(url);
  }

  public void spotifyRequest(String url_parameter) {
      if (mAccessToken == null) {
          if (getContext() != null) {
              Toast.makeText(getContext(), "You need to get an access token first!", Toast.LENGTH_SHORT).show();
          }
          return;
      }

      // Create a request to get the user profile
      final Request request =
              new Request.Builder()
                      .url(url_parameter)
                      .addHeader("Authorization", "Bearer " + mAccessToken)
                      .build();

      cancelCall();
      mCall = mOkHttpClient.newCall(request);

      mCall.enqueue(new Callback() {
          @Override
          public void onFailure(Call call, IOException e) {
              Log.d("HTTP", "Failed to fetch data: " + e);
              if (getContext() != null) {
                  Toast.makeText(getContext(), "Failed to fetch data, watch Logcat for more details", Toast.LENGTH_SHORT).show();
              }
          }

          @Override
          public void onResponse(Call call, Response response) throws IOException {
              if (!response.isSuccessful()) {
                  Log.d("HTTP", "Server returned error: " + response);
                  return;
              }
              try {
                  final JSONObject jsonObject = new JSONObject(response.body().string());
                  setTextAsync(jsonObject.toString(3), profileTextView);
                  String email = jsonObject.optString("email", "");
                  String id = jsonObject.optString("id", "");
                  if (getContext() != null) {
                      getActivity().runOnUiThread(() -> {
                          signUpSpotifyWrappedAccount(email, id);
                      });
                  }
              } catch (JSONException e) {
                  Log.d("JSON", "Failed to parse data: " + e);
                  if (getContext() != null) {
                      Toast.makeText(getContext(), "Failed to parse data, watch Logcat for more details", Toast.LENGTH_SHORT).show();
                  }
              }
          }
      });
  }

  private void playgame2() {
      // Select a random song from the top 5 songs and strip 4 random characters from the song name
      // The user has to guess the song name
      if (top5Songs.size() == 0) {
          Toast.makeText(getContext(), "You need to generate a wrapped first!", Toast.LENGTH_SHORT).show();
          return;
      }
      int randomIndex = (int) (Math.random() * top5Songs.size());
      String song = top5Songs.get(randomIndex);
      String song_name = song;
      for (int i = 0; i < 4; i++) {
          int randomChar = (int) (Math.random() * song_name.length());
          song_name = song_name.substring(0, randomChar) + "_" + song_name.substring(randomChar + 1);
      }
      // Display the song name with 4 characters stripped in the profileTextView
      setTextAsync("Guess the song: " + song_name, profileTextView);

      // TODO: Implement a way to check if the user guessed the song correctly
      // TODO: Add interface to allow the user to input their guess
  }

  private void spotifyRequest_playlist_songs(String playlistId) {
      String url_tracks = "https://api.spotify.com/v1/playlists/" + playlistId + "/tracks";
      Request request =
              new Request.Builder()
                      .url(url_tracks)
                      .addHeader("Authorization", "Bearer " + mAccessToken)
                      .build();

      mCall = mOkHttpClient.newCall(request);
      mCall.enqueue(
              new Callback() {
                  @Override
                  public void onFailure(Call call, IOException e) {
                      Log.d("HTTP", "Failed to fetch tracks: " + e);
                  }

                  @Override
                  public void onResponse(Call call, Response response) throws IOException {
                      try {
                          final JSONObject jsonObject = new JSONObject(response.body().string());
                          save_songs_from_playlist(jsonObject, playlistId);

                      } catch (JSONException e) {
                          Log.d("JSON", "Failed to parse data: " + e);
                          getActivity().runOnUiThread(
                                  () ->
                                          Toast.makeText(
                                                          getActivity(),
                                                          "Failed to parse data, watch Logcat for more details",
                                                          Toast.LENGTH_SHORT)
                                                  .show());
                      }
                  }
              });
  }

  private void save_playlist(final JSONObject json, TextView textView) {
      playlists = parsePlaylist(json);
  }

  private void save_songs_from_playlist(final JSONObject json, String playlistId) {
      ArrayList<String> songs = new ArrayList<>();
      try {
          JSONArray items = (JSONArray) json.get("items");

          for (int i = 0; i < items.length(); i++) {
              // Suponiendo que top_artists es un JSONObject y 'items' es un JSONArray dentro de él

              // i es tu índice en el bucle o algún valor específico
              JSONObject item = (JSONObject) items.get(i);
              // Now you can safely call get(key) on the JSONObject
              JSONObject track = item.getJSONObject("track");
              songs.add(track.getString("name")); // Use getString to directly get the String value
          }

      } catch (JSONException e) {
          Log.d("JSON", "Failed to parse data: " + e);
          Toast.makeText(
                          getActivity(),
                          "Failed to parse data, watch Logcat for more details",
                          Toast.LENGTH_SHORT)
                  .show();
      }
      playlist_songs.put(playlistId, songs);
  }

  public void signUpSpotifyWrappedAccount(String email, String id) {
      String TAG = "SpotifyWrapped Sign Up";
      // Ensure we have a valid Activity before attempting to sign up
      if (getActivity() == null) {
          // Optionally handle the case where the activity is not available
          return;
      }
      mAuth
              .createUserWithEmailAndPassword(email, id)
              .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                      if (task.isSuccessful()) {
                          Log.d(TAG, "Created user with " + email + " and password: " + id + " successfully");
                      } else {
                          // Optionally handle the case where sign up fails
                          Log.d(TAG, "Sign up failed", task.getException());
                      }
                  }
              });
  }

  public Map<String, Pair<String, String>> parsePlaylist(JSONObject json_value) {
      Map<String, Pair<String, String>> hash_vals = new HashMap<>();
      try {
          JSONArray items = (JSONArray) json_value.get("items");

          for (int i = 0; i < items.length(); i++) {
              // Suponiendo que top_artists es un JSONObject y 'items' es un JSONArray dentro de él

              // i es tu índice en el bucle o algún valor específico
              JSONObject item = (JSONObject) items.get(i);
              // Now you can safely call get(key) on the JSONObject
              // Get the image
              JSONArray images = item.getJSONArray("images");
              String image = "";
              if (images.length() > 0) {
                  JSONObject image_obj = images.getJSONObject(0);
                  image = image_obj.getString("url");
              }

              hash_vals.put(
                      item.getString("name"),
                      new Pair<>(
                              item.getString("id"), image)); // Use getString to directly get the String value
          }

      } catch (JSONException e) {
          Log.d("JSON", "Failed to parse data: " + e);
          Toast.makeText(
                          getContext(),
                          "Failed to parse data, watch Logcat for more details",
                          Toast.LENGTH_SHORT)
                  .show();
      }

      return hash_vals;
  }
  */
}
