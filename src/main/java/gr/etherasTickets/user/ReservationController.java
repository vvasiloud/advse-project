package gr.etherasTickets.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{userid}/reservations")
public class ReservationController {

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> addReservation(@RequestParam String userid , @RequestBody Reservation newReservation){
		
		return new ResponseEntity<String>("" , HttpStatus.OK);
	}
}
