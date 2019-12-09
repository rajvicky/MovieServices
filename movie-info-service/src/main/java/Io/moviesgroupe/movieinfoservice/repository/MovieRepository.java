package Io.moviesgroupe.movieinfoservice.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import Io.moviesgroupe.movieinfoservice.model.Movie;
@Repository
public interface MovieRepository extends JpaRepository <Movie, Integer>{
	
	@Query("SELECT movie FROM Movie movie WHERE movie.name=:name")
	public Movie GetMovieByName(@Param("name") String name );
	
	@Query("SELECT movie FROM Movie movie WHERE movie.movieId=:Id")
	public Movie GetMovieById(@Param("Id") int Id);
	
	@Query("SELECT movie FROM Movie movie WHERE movie.name LIKE %:keyword%")
	public List<Movie> GetMoviesSearhByKeyword(@Param("keyword") String keyword);
	
	@Query("SELECT movie FROM Movie movie ORDER BY movie.name DESC")
	public List<Movie> GetAllMoviesSortedByName();

}

