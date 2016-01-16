package gr.etherasTickets.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users") // Table users creation message to MongoDB
public class User {

    @Id
    private String id;
    private String firstName;
    private String lastName;

    @Indexed(unique=true) //unique for username
    private String username;
    private String email;
    private String password;
    private double balance;
    private List<Reservation> reservations = new ArrayList<>();

    public User(String firstName, String lastName,String email, String username, String password,double balance) {
    	this(firstName , lastName , email , username , password , balance ,new ArrayList<Reservation>());
    }
    
    public User(String firstName, String lastName,String email, String username, String password,double balance,List<Reservation> reservations) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.reservations = reservations;
    }
    
    public User(){
    	
    }
    
    public void addReservation(Reservation newReservation){
    	reservations.add(newReservation);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
    
    public void showReservations(){
    	for (Reservation r : reservations){
    		System.out.println("FROM : " + r.getFlight().getFrom());
    		System.out.println("TO : " + r.getFlight().getTo());
    		System.out.println("DATE : " + r.getFlight().getDate());
    		System.out.println("\n");
    	}
    }
    
    public void changeUserData(User otherUser){
    	if(otherUser.getFirstName()!= null)this.setFirstName(otherUser.getFirstName());
    	if(otherUser.getLastName()!= null)this.setLastName(otherUser.getLastName());
    	if(otherUser.getEmail()!= null)this.setEmail(otherUser.getEmail());
    	if(otherUser.getUsername()!= null)this.setUsername(otherUser.getUsername());
    	if(otherUser.getPassword()!= null)this.setPassword(otherUser.getPassword());
    	
    }
}