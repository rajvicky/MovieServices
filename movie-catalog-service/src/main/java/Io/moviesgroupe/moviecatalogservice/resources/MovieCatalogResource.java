package Io.moviesgroupe.moviecatalogservice.resources;
import java.util.List;
import java.util.stream.*;
import java.net.URI;

import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import Io.moviesgroupe.moviecatalogservice.model.*;
@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId")String userId){
		
		URI uri=URI.create("http://localhost:8093/rating/user"+userId);
		UserRating ratingList=restTemplate.getForObject(uri,UserRating.class);
		return ratingList.getUserRatings().stream().map(rating->{

		 URI uriMovie=URI.create("http://localhost:8093/movieinfo/"+rating.getMovieId());
		Movie movie=restTemplate.getForObject(uriMovie,Movie.class);
		return new CatalogItem(movie.getName(),"Nothing",Integer.parseInt(rating.getRating()));
		}).collect(Collectors.toList());
	}
}
