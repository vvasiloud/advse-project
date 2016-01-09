package gr.etherasTickets.user;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import gr.etherasTickets.EtherasTicketsApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EtherasTicketsApplication.class, loader = AnnotationConfigContextLoader.class)

public class UserTest {
	@Autowired 
	private UserRepository repository;
	private User expectedUser;

	@Before
    public void setUp() {
		//create test user
		expectedUser = new User("23459","Vasilis","Georgakopoulos","tralala@mail.com","vgeorga","pass123",0.0, new ArrayList<Reservation>());
        System.out.println("@Before - setUp");
    }
		
	
	@Test
	public void testCreateUser(){
		//insert user inside mongoDB
		repository.createUser("23459","Vasilis","Georgakopoulos","tralala@mail.com","vgeorga","pass123",0.0, new ArrayList<Reservation>());
		System.out.println("#########################>User inserted into database<#########################");
		User actualUser = repository.getUserById("23459");
		assertEquals(expectedUser.getUsername(), actualUser.getUsername());
	}
	
	@Test
	public void testLogin(){
		
		
	}



	}
