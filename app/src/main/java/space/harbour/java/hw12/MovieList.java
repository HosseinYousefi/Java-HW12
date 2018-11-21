package space.harbour.java.hw12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class MovieList extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		final String API_ADDRESS = "https://api.myjson.com/bins/nfvfi";
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie_list);
		new ConnectionHandler(this).execute(API_ADDRESS);
	}
}
