package com.example.simpleSpringBoot;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class HomeRestController {
	@RequestMapping("/hello")
	String GetInForMation() {
		return "HEllo";
	}

}
