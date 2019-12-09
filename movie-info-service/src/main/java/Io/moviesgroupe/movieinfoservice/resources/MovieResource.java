package Io.moviesgroupe.movieinfoservice.resources;

import java.net.URI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import Io.moviesgroupe.movieinfoservice.model.BitPayRate;

import Io.moviesgroupe.movieinfoservice.model.Movie;
import Io.moviesgroupe.movieinfoservice.model.MovieDetails;
import Io.moviesgroupe.movieinfoservice.model.MovieList;
import Io.moviesgroupe.movieinfoservice.model.UpComingMovie;
import Io.moviesgroupe.movieinfoservice.model.UserRating;

//import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;


@RestController
//@EnableHystrix
@RequestMapping("/movieinfo")
public class MovieResource {
	@Autowired
	private RestTemplate restTemplate;
	//@Autowired
	//private MovieRepository movieRepository;
	private Logger logger=org.slf4j.LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MovieResourcesProxy proxy;
	@GetMapping("/user/{ratingId}")
	public UserRating getMovieRating(@PathVariable("ratingId") int ratingId){
		logger.info("Message", ratingId);
		return proxy.getRatingList(ratingId);
	}
	@GetMapping("/from/{ratingId}")
	public UserRating getMovieRatings(@PathVariable("ratingId") int ratingId){
		logger.info("Message", ratingId);
		return proxy.getRatingList(ratingId);
		
	}
		
	@HystrixCommand(fallbackMethod = "GetMovieInfo",commandKey = "GetMovie",groupKey = "GetMovie")
	@RequestMapping("/{movieId}")
	public Movie getmovieInfo(@PathVariable("movieId") int movieId){
		ResponseEntity<List<BitPayRate>> response=restTemplate.exchange(
				  "https://bitpay.com/api/rates",
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<BitPayRate>>(){});
		List<BitPayRate> rate=response.getBody();
		rate.forEach(System.out::println);
		//List<Movie> listMovie= movieRepository.findAll();
		//listMovie.forEach(System.out::println);
		return null;
		
	}
	@RequestMapping("/movieData")
	public MovieList getMovieData() {
		
		URI uri=URI.create("https://api.themoviedb.org/3/discover/movie?api_key=9e342d75d76221c54b63cf310e3950be&sort_by=revenue.asc&include_adult=true&include_video=true");
		MovieList movieList=restTemplate.getForObject(uri,MovieList.class);
		movieList.getResults().forEach(System.out::println);
	
		return movieList;
	}
	
	@RequestMapping("/latest")
	public MovieList GetLateMovieList(@RequestParam Map<String ,String> allParms)
	{
		System.out.println(allParms.get("api_key"));
		//https://api.themoviedb.org/3/movie/latest?api_key=<<api_key>>&language=en-US
		URI uri=URI.create("https://api.themoviedb.org/3/movie/latest?api_key="+allParms.get("api_key"));
		MovieList movieList=restTemplate.getForObject(uri,MovieList.class);
		
		return movieList;
	}
	@RequestMapping("/topratemovie")
	public MovieList GetTopRateMovieList(@RequestParam Map<String ,String> allParms)
	{
		System.out.println(allParms.get("api_key"));
		
		URI uri=URI.create("https://api.themoviedb.org/3/movie/top_rated?api_key="+allParms.get("api_key"));
		MovieList movieList=restTemplate.getForObject(uri,MovieList.class);
		
		return movieList;
	}
	
	@RequestMapping("/upcomingmovie")
	public UpComingMovie GetUpComingMovieList(@RequestParam Map<String ,String> allParms)
	{
		

//https://api.themoviedb.org/3/movie/top_rated?api_key=<<api_key>>&language=en-US&page=1
	//optional
	//SEND REQUEST
	//https://api.themoviedb.org/3/movie/upcoming?api_key=<<api_key>>&language=en-US&page=1
	
		URI uri=URI.create("https://api.themoviedb.org/3/movie/upcoming?api_key="+allParms.get("api_key"));
		UpComingMovie upComingMovie=restTemplate.getForObject(uri,UpComingMovie.class);
		
		return upComingMovie;
	}
	
	@GetMapping("/upcomingmoviebylangauge")
	public List<MovieDetails> GetUpComingMovieListByLangauge(@RequestParam Map<String ,String> allParms)
	{	
		URI uri=URI.create("https://api.themoviedb.org/3/movie/upcoming?api_key="+allParms.get("api_key")+"&language="+allParms.get("language"));
		UpComingMovie upComingMovie=restTemplate.getForObject(uri,UpComingMovie.class);
		System.out.println(allParms.get("language"));
		
		upComingMovie.getResults().forEach(movie->{
			System.out.print(movie.getOriginal_language());
		});
		List<MovieDetails> movies=upComingMovie.getResults().stream().filter(movie->movie.getOriginal_language().equals(allParms.get("language")))
		.collect(Collectors.toList());
		return movies;
	}
	
	@GetMapping("/moviebylanguage")
	public List<MovieDetails> GetMovieByLanguage(@RequestParam Map<String,String> allParam) {
		
		URI uri=URI.create("https://api.themoviedb.org/3/discover/movie?api_key="+allParam.get("api_key")+"&language="+ allParam.get("language")+"&region="+allParam.get("region"));
		MovieList movieList=restTemplate.getForObject(uri,MovieList.class);
		movieList.getResults().forEach(System.out::println);
		List<MovieDetails> allMovieByLanguage=new ArrayList<>();
		for(int i=1;i<=movieList.getTotal_pages();i++)
		{
			 uri=URI.create("https://api.themoviedb.org/3/discover/movie?api_key="+allParam.get("api_key")+"&language="+ allParam.get("language")+"&region="+allParam.get("region")+"&page="+i);
			 movieList=restTemplate.getForObject(uri,MovieList.class);
			 allMovieByLanguage.addAll(movieList.getResults());
		}
	   allMovieByLanguage=allMovieByLanguage.stream().filter(movieDetails->movieDetails.getOriginal_language().equals(allParam.get("language")))
	   .collect(Collectors.toList());
	
		return allMovieByLanguage;
	}
	
	@GetMapping("/moviebydate")
	public List<MovieDetails> GetMovieReleaseDate(@RequestParam Map<String,String> allParam)
	{
		URI uri=URI.create("https://api.themoviedb.org/3/discover/movie?api_key="+allParam.get("api_key")+"&language="+allParam.get("language")+"&page=1&release_date.gte="+allParam.get("datett")+"&release_date.lte="+allParam.get("Dategt"));
		MovieList movieList=restTemplate.getForObject(uri,MovieList.class);
		List<MovieDetails> listMovieDetails=new ArrayList<MovieDetails>();
		for(int i=1;i<movieList.getPage();i++)
		{
			uri=URI.create("https://api.themoviedb.org/3/discover/movie?api_key="+allParam.get("api_key")+"&language="+allParam.get("language")+"&page=1&release_date.gte="+allParam.get("datett")+"&release_date.lte="+allParam.get("Dategt")+"&page="+i);
			movieList=restTemplate.getForObject(uri,MovieList.class);
			listMovieDetails.addAll(movieList.getResults());
		}
		listMovieDetails=listMovieDetails.stream().filter(movie->movie
				.getOriginal_language().equals(allParam.get("language"))).collect
		(Collectors.toList());
		return listMovieDetails;
	}
	@GetMapping("/moviebyalphabatic")
	public List<MovieDetails> GetMovieByAlphabatic(@RequestParam Map<String,String> allParam) {
		
		URI uri=URI.create("https://api.themoviedb.org/3/discover/movie?api_key="+allParam.get("api_key")+"&language="+ allParam.get("language")+"&region="+allParam.get("region"));
		MovieList movieList=restTemplate.getForObject(uri,MovieList.class);
		movieList.getResults().forEach(System.out::println);
		List<MovieDetails> allMovieByLanguage=new ArrayList<>();
		for(int i=1;i<=movieList.getTotal_pages();i++)
		{
			 uri=URI.create("https://api.themoviedb.org/3/discover/movie?api_key="+allParam.get("api_key")+"&language="+ allParam.get("language")+"&region="+allParam.get("region")+"&page="+i);
			 movieList=restTemplate.getForObject(uri,MovieList.class);
			 allMovieByLanguage.addAll(movieList.getResults());
		}
		Predicate<String> matchingWithAlphabaticWay=(s)->s.startsWith(allParam.get("Alphabet"));
	    allMovieByLanguage=allMovieByLanguage.stream().
			   filter(movieDetails->movieDetails.getOriginal_language().equals(allParam.get("language")))
			   .filter(movieDetails->{
				   return matchingWithAlphabaticWay.test(movieDetails.getTitle());
			   }).
			   collect(Collectors.toList());
	
		return allMovieByLanguage;
	}
	
	public Movie GetMovieInfo() {
		return null;
	}
}