package gr.etherasTickets.user;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{userid}/reservations")
public class ReservationController {

	@RequestMapping(method = RequestMethod.POST)
	public void addReservation(@RequestParam String userid , @RequestBody Reservation newReservation){
		
	}
}
