package gr.etherasTickets.user;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
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
	private User dummyUser;
 
    
	@Before
    public void setUp() {
		//create test user
		expectedUser = new User("Vasilis","Georgakopoulos","tralala@mail.com","vgeorga","pass123",0.0, new ArrayList<Reservation>());
		dummyUser = new User("Bill","Preloudios","null@null.com","testUser","pass123",0.0, new ArrayList<Reservation>());

		repository.insert(dummyUser);
        System.out.println("@Before - setUp");
    }

	@After
    public void tearDown() {
		//delete what you have created
		repository.delete(dummyUser);
        System.out.println("@After - tearDown");
    }

	@Test
	public void testGetUser(){
		//update username
		User expectedUser = repository.findByUsername("testUser");
		User actualUser = repository.getUserById(expectedUser.getId());

		assertEquals(actualUser.getUsername(),"testUser");

	}

	@Test
	public void testCreateUser(){
		//insert user inside mongoDB
		repository.createUser(expectedUser);
		System.out.println("#########################>User inserted into database<#########################");
		User actualUser = repository.findByUsername("vgeorga");
		assertEquals(expectedUser.getUsername(), actualUser.getUsername());
	}
	
	
	@Test
	public void testRemoveUser(){
		//delete user
		User expected = new User("Bill","Preloudios","null@null.com","deleteUser","pass123",0.0, new ArrayList<Reservation>());
		repository.insert(expected);
		repository.removeUser(expected.getId());
		User actual = repository.findByUsername("deleteUser");
		assertNull(actual);
	
	}
	
	}
