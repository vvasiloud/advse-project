package gr.etherasTickets.user;

import static org.junit.Assert.assertEquals;

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

public class UpdateUserTest {
	@Autowired 
	private UserRepository repository;
	private User beforeUpdateUser;
	private User updatedUser;
 
	@Before
	public void SetUp(){
		repository.deleteAll();
	}
    
	@Test
    public void testUpdateUser() {
		//create test user
		beforeUpdateUser = new User("Vasilis","Georgakopoulos","tralala@mail.com","vgeorga","pass123",0.0, new ArrayList<Reservation>());
		repository.insert(beforeUpdateUser); //insert into db
		System.out.println("*********************************");
		System.out.println("BEFORE UPDATE");
		System.out.println(beforeUpdateUser.getFirstName());
		System.out.println(beforeUpdateUser.getLastName());
		System.out.println(beforeUpdateUser.getEmail());
		System.out.println(beforeUpdateUser.getUsername());
		System.out.println(beforeUpdateUser.getPassword());
		System.out.println("*********************************");
		updatedUser = new User("Nikos", "Samaras", "newmail@tmail.com", "nikosam", "newpass345",0.0, new ArrayList<Reservation>());
		repository.updateUser(beforeUpdateUser.getId(), updatedUser); //update user
		updatedUser=repository.getUserById(beforeUpdateUser.getId());
		System.out.println("*********************************");
		System.out.println("AFTER UPDATE");
		System.out.println(updatedUser.getFirstName());
		System.out.println(updatedUser.getLastName());
		System.out.println(updatedUser.getEmail());
		System.out.println(updatedUser.getUsername());
		System.out.println(updatedUser.getPassword());
		System.out.println("*********************************");
		assertEquals(beforeUpdateUser.getId(),updatedUser.getId());
    }


	
}
