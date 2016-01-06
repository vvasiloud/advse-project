package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {
	private static final String template = "Hello aefaef, %s!";
	private final AtomicLong counter = new AtomicLong();
	

	

	public GreetingController(){

	}
	
	@RequestMapping(path = "/greeting" , method = {RequestMethod.GET} , produces={"application/json"})
	public Greeting greeting(@RequestParam(value = "name" , defaultValue = "World") String name){
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
	
	

	
}
