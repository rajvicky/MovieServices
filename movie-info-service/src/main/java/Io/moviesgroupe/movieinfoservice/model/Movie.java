package Io.moviesgroupe.movieinfoservice.model;
import javax.persistence.*;

import org.hibernate.annotations.Type;
@Entity
@Table(name="Movie")
public class Movie {
	@Id
	@GeneratedValue
	private int movieId;
	
	@Column(name="Movie_Name")
	private String name;
	
	public Movie() {
		
	}
	
	public Movie(int movieId, String name) {
		this.movieId = movieId;
		this.name = name;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", name=" + name + "]";
	}

}
