package space.harbour.java.hw12;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

	protected List<Movie> moviesList;

	public MovieAdapter(List<Movie> moviesList) {
		this.moviesList = new ArrayList<>();
		for (Movie m: moviesList)
			this.moviesList.add(m);
	}

	class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		private TextView movieTitle;

		public MovieHolder(@NonNull View itemView) {
			super(itemView);
			itemView.setOnClickListener(this);
			movieTitle = itemView.findViewById(R.id.movie_title);
		}

		@Override
		public void onClick(View v) {
			Toast.makeText(v.getContext(), movieTitle.getText(), Toast.LENGTH_SHORT).show();
		}

		public void setMovie(Movie movie) {

			movieTitle.setText(movie.title);
		}
	}

	@NonNull
	@Override
	public MovieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
		View v = inflater.inflate(R.layout.activity_movie_list, viewGroup, false);
		return new MovieHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull MovieHolder myViewHolder, int i) {
		Movie movie = moviesList.get(i);
		myViewHolder.setMovie(movie);
	}

	@Override
	public int getItemCount() {
		return moviesList.size();
	}
}
