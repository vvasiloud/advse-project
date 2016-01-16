package gr.etherasTickets.user;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReservationRepository extends MongoRepository<Reservation, String>, CustomReservationRepository {


}
