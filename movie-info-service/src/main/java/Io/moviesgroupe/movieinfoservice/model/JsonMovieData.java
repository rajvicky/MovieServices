package Io.moviesgroupe.movieinfoservice.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;


public class JsonMovieData {
	
	private String title;
    private int year;
    private List<String> cast=new ArrayList<String>();
    private List<String> genres=new ArrayList<String>();
	
	public JsonMovieData() {
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public List<String> getCast() {
		return cast;
	}

	public void setCast(List<String> cast) {
		this.cast = cast;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public JsonMovieData(String title, int year, List<String> cast, List<String> genres) {
		super();
		this.title = title;
		this.year = year;
		this.cast = cast;
		this.genres = genres;
	}

	@Override
	public String toString() {
		return "JsonMovieData [title=" + title + ", year=" + year + ", cast=" + cast + ", genres=" + genres + "]";
	}
	
}
