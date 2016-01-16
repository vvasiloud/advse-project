package gr.etherasTickets.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{userid}/reservations")
public class ReservationController {
	
	ReservationRepository repository;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> addReservation(@RequestParam String userid , @RequestBody Reservation newReservation){
		
		return new ResponseEntity<String>("" , HttpStatus.OK);
	}
	
	 @RequestMapping(path = "/cancelReservations/{reservationId}" , method = RequestMethod.DELETE)
	    public ResponseEntity<Reservation> removeReservation(@RequestParam String reservationId ){
	        Reservation reservation = repository.getReservationById(reservationId);
	        if(reservation == null){
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }

	        repository.removeReservation(reservationId);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    
}
