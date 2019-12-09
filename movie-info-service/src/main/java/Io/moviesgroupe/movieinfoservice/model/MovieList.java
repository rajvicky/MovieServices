package Io.moviesgroupe.movieinfoservice.model;

import java.util.List;

public class MovieList {
	  private int page;
	  private int total_results;
	  private int total_pages;
	  private List<MovieDetails>results;
	public MovieList() {
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotal_results() {
		return total_results;
	}
	public void setTotal_results(int total_results) {
		this.total_results = total_results;
	}
	public int getTotal_pages() {
		return total_pages;
	}
	public void setTotal_pages(int total_pages) {
		this.total_pages = total_pages;
	}
	public List<MovieDetails> getResults() {
		return results;
	}
	public void setResults(List<MovieDetails> results) {
		this.results = results;
	}
	
	@Override
	public String toString() {
		return "MovieList [page=" + page + ", total_results=" + total_results + ", total_pages=" + total_pages
				+ ", results=" + results + "]";
	}
	      
}
