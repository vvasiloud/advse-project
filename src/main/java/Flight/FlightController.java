package Flight;

import org.springframework.web.bind.annotation.*;

@RestController
public class FlightController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String getFlights(){
		return "hello World";
	}
	
	@RequestMapping(path = "/flights" , method = RequestMethod.POST)
	public String creatFlights(){
		return "hello World";
	}
}
