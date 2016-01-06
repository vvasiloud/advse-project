package Flight;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flights")
public class FlightController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String getFlights(){
		return "hello World affsafafs";
	}
	
}
