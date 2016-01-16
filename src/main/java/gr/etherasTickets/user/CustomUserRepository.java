package gr.etherasTickets.user;

public interface CustomUserRepository {
    public User getUserById(String id);
    public User createUser(User user);
    public String userLogin(String username, String password);
    public void removeUser(String id);
<<<<<<< HEAD
    public void updateUser(String id, String firstname, String lastname, String email, String username, String password);


=======
>>>>>>> 004a4ccac7317aac08b54ebd2d8abcf5c405bcbf
}
