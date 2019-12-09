package Io.moviesgroupe.movieinfoservice.resources;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import Io.moviesgroupe.movieinfoservice.model.UserRating;

//@FeignClient(name="movie-rating-service",url = "localhost:8095")
//@FeignClient(name="movie-rating-service")
@FeignClient(name="API-Gateway")
@RibbonClient(name="movie-rating-service")
public interface MovieResourcesProxy {
	//@GetMapping("/rating/user/{userId}")
	@GetMapping("/movie-rating-service/rating/user/{userId}")
	public UserRating getRatingList(@PathVariable("userId")int userId);
}
