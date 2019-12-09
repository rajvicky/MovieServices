package Io.moviesgroupe.movieratingservice.resource;
import Io.moviesgroupe.movieratingservice.config.*;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import Io.moviesgroupe.movieratingservice.model.LimitConfiguration;
import Io.moviesgroupe.movieratingservice.model.Rating;
import Io.moviesgroupe.movieratingservice.model.UserRating;
@RestController
@RequestMapping("/rating")
public class RatingResource {
	private Logger logger=org.slf4j.LoggerFactory.getLogger(this.getClass());
	@Autowired
	public Configuration configuration;
	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId,"4");
	}

	@GetMapping("/limit")
	public LimitConfiguration getLimitConfiguration() {
		return new LimitConfiguration(configuration.getMaximum(),configuration.getMin());
	}
	
	@RequestMapping("user/{userId}")
	public UserRating getRatingList(@PathVariable("userId")int userId){
		logger.info("inside Rating",userId);
		List<Rating> listRating= Arrays.asList(
				new Rating("1234","7"),
				new Rating("12345","78")
				);
		UserRating userRating=new UserRating();
	    userRating.setUserRatings(listRating);
	    return userRating;
				
	}
	
}
