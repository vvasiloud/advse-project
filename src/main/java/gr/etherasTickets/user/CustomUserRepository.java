package gr.etherasTickets.user;

public interface CustomUserRepository {
    public User getUserById(String id);
    public User createUser(User user);
    public String userLogin(String username, String password);
    public void removeUser(String id);
}
