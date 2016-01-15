package gr.etherasTickets.user;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import gr.etherasTickets.EtherasTicketsApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EtherasTicketsApplication.class, loader = AnnotationConfigContextLoader.class)

public class CreateUserTest {
	@Autowired 
	private UserRepository repository;
   
	@Test
	public void testCreateUser(){
		//insert user inside mongoDB
		//repository.createUser("6575675676","Spuros","Ampatzoglou","534hde@mail.com","werew","w23432234",0.0, new ArrayList<Reservation>());
		repository.createUser("34543","Dimitris","SmashBros","mail@mail.com","heyYOU","5983uer9re843",0.0, new ArrayList<Reservation>());
		System.out.println("#########################>User inserted into database<#########################");	
	}
		
}
