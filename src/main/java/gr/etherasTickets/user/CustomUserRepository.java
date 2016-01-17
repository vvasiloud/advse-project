package gr.etherasTickets.user;

import gr.etherasTickets.exceptions.RestException;

public interface CustomUserRepository {
    public User getUserById(String id) throws RestException;
    public User createUser(User user);
    public String userLogin(String username, String password);
    public void removeUser(String id);
    public void updateUser(String id, User user);
}
