package gr.etherasTickets.user;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Date;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;


import gr.etherasTickets.EtherasTicketsApplication;
import gr.etherasTickets.exceptions.BadArguments;
import gr.etherasTickets.exceptions.NotFound;
import gr.etherasTickets.flight.Flight;
import gr.etherasTickets.flight.FlightRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EtherasTicketsApplication.class, loader = AnnotationConfigContextLoader.class)


public class ReservationTest {
	
	
	@Autowired 
	private UserRepository usersRepository;
	private User showReservationsUser;
	private User afterInsertUser;
	
	@Autowired
	private FlightRepository flightRepository;
	
	
	@Before
	public void SetUp(){
		
	
		usersRepository.deleteAll();
		System.out.println(usersRepository);
		usersRepository.save(new User("test" , "testopoulos" , "testopoulos@test.com" , "test372" , "1234" , 0));

		
	}
	
	/*
	@Test
	public void addReservationTest(){
		
	}
<<<<<<< HEAD
	*/
	@Test
	public void showReservationTest(){
		/*
		showReservationsUser = new User("Trollos","Trollopoulos","trololololol@mail.com","troll","somePass1234",0, new ArrayList<Reservation>());
		Reservation dummyResrv = new Reservation(new Flight("Thessaloniki", "Athens", 50, 60, new Date()), 5,new Date());
		showReservationsUser.addReservation(dummyResrv);
		usersRepository.insert(showReservationsUser);
		afterInsertUser = usersRepository.getUserById(showReservationsUser.getId());
		assertEquals(showReservationsUser.getId(),afterInsertUser.getId());	
		*/
	}

	
	@Test
	public void removeReservationTest() throws BadArguments, NotFound{
		
		
		
		
	}
	
	
}
