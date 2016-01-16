package gr.etherasTickets.user;

public interface CustomUserRepository {
    public User getUserById(String id);
    public User createUser(User user);
    public String userLogin(String username, String password);
    public void removeUser(String id);
    public void updateUser(String id, User user);
    public void showUserReservations(String id);


}
