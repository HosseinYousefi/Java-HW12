package space.harbour.java.hw12;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

public class ConnectionHandler extends AsyncTask<String, Void, Void> {

    MovieList movieList;
    List<Movie> movies;

    public ConnectionHandler(MovieList movieList) {
        this.movieList = movieList;
        movies = new ArrayList<>();
    }

    @Override
    protected Void doInBackground(String... strings) {
        Log.i("Connector", "Started downloading");
        try  {
            URL url = new URL(strings[0]);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            InputStream input = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
                sb.append(line + "\n");
            String result = sb.toString();
            javax.json.JsonReader jsonReader = Json.createReader(new StringReader(result));
            JsonArray jsonArray = jsonReader.readArray();
            for (int i = 0; i < jsonArray.size(); ++i) {
                Movie elem = new Movie();
                elem.fromJson(jsonArray.getJsonObject(i).toString());
                movies.add(elem);
            }
        } catch (MalformedURLException e) {
            Log.e("doInBackground", "URL invalid");
        } catch (IOException e) {
            Log.e("doInBackground", "IO Error");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MovieAdapter listAdapter = new MovieAdapter(movies);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(movieList);
        RecyclerView list = movieList.findViewById(R.id.list);
        list.setAdapter(listAdapter);
        list.setLayoutManager(manager);
    }
}
