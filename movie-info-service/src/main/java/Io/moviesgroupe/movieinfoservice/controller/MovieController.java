package Io.moviesgroupe.movieinfoservice.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import Io.moviesgroupe.movieinfoservice.repository.MovieRepository;
import Io.moviesgroupe.movieinfoservice.Exception.MovieNotFoundException;
import Io.moviesgroupe.movieinfoservice.model.*;

@RestController
@RequestMapping("/MovieDromDataBase")
public class MovieController {
	
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	@Qualifier("helloServicePython")
	private HelloService service;
	
	@GetMapping("/Hello")
	private String GetHelloMessage(){
		System.out.println(service.GetHello());
		 return service.GetHello();
	 }
 
	@GetMapping("/Movies")
	public List<Movie> getAllMovies(){
		List<Movie> movies=movieRepository.findAll();
		
		 
		return movies;
	}
	@GetMapping("/Movies/moviebyname")
	public Movie getMovieByName(@RequestParam("name") String name)
	{
		Movie movie=movieRepository.GetMovieByName(name);
		if(movie==null)
			throw new MovieNotFoundException("MovieName"+name);
		
		return movie;
	}
	

	@GetMapping("/Movies/movieById/{Id}")
	public Resource<Movie> GetMovieById(@PathVariable("Id") int Id)
	{
		/*URI location=ServletUriComponentsBuilder.
		fromCurrentRequest().path("/{Id}").
		buildAndExpand(movieRepository.GetMovieById(Id).getMovieId()).
		toUri();*/
		Movie movie= movieRepository.GetMovieById(Id);
		   Resource<Movie> resource=new Resource<>(movie);
		    ControllerLinkBuilder link= linkTo(methodOn(this.getClass()).getAllMovies());
		    resource.add(link.withSelfRel());
		return resource;
	}
    @GetMapping("/Movies/Search")
    public List<Movie> GetMoviesSearchByKeyword(@RequestParam("keyword") String keyword)
    {
    	System.out.println(keyword);
    	return movieRepository.GetMoviesSearhByKeyword(keyword);
    }
  
    @PostMapping("/Movies/AddMovie")
    public ResponseEntity<Movie> AddMovie(@RequestBody Movie movie)
    {
    	System.out.println(movie.toString());
    	Movie savedMovie=movieRepository.save(movie);
    	String movieLocation=ServletUriComponentsBuilder.
    			fromUriString("http://localhost:8012/MovieDromDataBase/Movies/movieById/"+savedMovie.getMovieId()).toUriString();
    	System.out.println(movieLocation);
    	URI location =ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		.buildAndExpand(savedMovie.getMovieId()).toUri();
    	return ResponseEntity.created(location).build();//accepted().headers(new HttpHeaders()).body(savedMovie);
    }
    @GetMapping("/Movies/MovieBySortName")
    public List<Movie> GetAllMoviesBySortedOrder()
    {
    	System.out.println("Get All these movies by sorted Order");
    	List<Movie> movies=movieRepository.findAll(new Sort(Sort.Direction.DESC,"name"));
    	movies.forEach(System.out::println);
    	return movieRepository.GetAllMoviesSortedByName();
    }
    
    @DeleteMapping("/Movies/{Id}")
	public void DeleteMovieById(@PathVariable("Id") int Id)
	{      
    	 try
			{
		         movieRepository.deleteById(Id);
			}
      catch (Exception e) {
				throw new MovieNotFoundException("Movie is not found"+Id);
			}   
	}    
}
