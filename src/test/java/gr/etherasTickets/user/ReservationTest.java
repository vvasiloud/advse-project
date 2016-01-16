package gr.etherasTickets.user;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ReservationTest {
	@Autowired 
	private ReservationRepository repository;
	
	@Autowired 
	private UserRepository usersRepository;
	
	@Before
	public void SetUp(){
		usersRepository.deleteAll();
		usersRepository.save(new User("test" , "testopoulos" , "testopoulos@test.com" , "test372" , "1234" , 0));
	}
	
	@Test
	public void addReservationTest(){
		
	}
}
