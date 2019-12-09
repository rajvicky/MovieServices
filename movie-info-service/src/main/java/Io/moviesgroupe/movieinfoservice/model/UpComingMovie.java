package Io.moviesgroupe.movieinfoservice.model;

import java.util.List;

public class UpComingMovie {
	 private List<MovieDetails>results;
		public UpComingMovie() {
		}
		public List<MovieDetails> getResults() {
			return results;
		}
		public void setResults(List<MovieDetails> results) {
			this.results = results;
		}
}
