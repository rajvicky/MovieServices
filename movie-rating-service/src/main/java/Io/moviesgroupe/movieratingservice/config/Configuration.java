package Io.moviesgroupe.movieratingservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("movie-rating-service")
public class Configuration {
	private int min;
	private int maximum;
	
	
	public Configuration() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Configuration(int min, int maximum) {
		super();
		this.min = min;
		this.maximum = maximum;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMaximum() {
		return maximum;
	}
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	

}
