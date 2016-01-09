package gr.etherasTickets.user;

import java.util.List;

public interface CustomUserRepository {
    public User getUserById(String id);
    public User createUser(String id, String firstname, String lastname, String email, String username, String password, double balance,List<Reservation> reservations);
    public String userLogin(String username, String password);

}
