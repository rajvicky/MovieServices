package Io.moviesgroupe.movieinfoservice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import Io.moviesgroupe.movieinfoservice.model.HelloService;
import Io.moviesgroupe.movieinfoservice.model.HelloServiceFactory;
import Io.moviesgroupe.movieinfoservice.repository.MovieRepository;
import brave.sampler.Sampler;

@SpringBootApplication
@EnableHystrix
@EnableHystrixDashboard
@EnableJpaRepositories(basePackageClasses = MovieRepository.class)
@EnableFeignClients
@EnableDiscoveryClient
public class MovieInfoServiceApplication {
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MovieInfoServiceApplication.class, args);
	}
	@Bean(name = "helloServiceFactory")
    public HelloServiceFactory helloFactory() {
    	return new HelloServiceFactory();
    }
    
    @Bean(name = "helloServicePython")
    public HelloService helloServicePython() throws Exception {
        return helloFactory().getObject();
    }
    @Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}
