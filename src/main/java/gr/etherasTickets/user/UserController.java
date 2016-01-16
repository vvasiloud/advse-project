package gr.etherasTickets.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired // new object on demand (dependency injection)
    private UserRepository repository;

    @RequestMapping(path = "/{id}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable String id){
        return new ResponseEntity<>(repository.getUserById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/auth" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> authorize(@RequestParam Map<String,String> requestParams) throws Exception{
        String username=requestParams.get("username");
        String password=requestParams.get("password");
        String response = repository.userLogin(username,password);
        Map<String,String> jsonResponse = new HashMap<String,String>();
        jsonResponse.put("_userId", response);
        return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody User user) throws Exception{
        User users = repository.findByUsername(user.getFirstName());
        if(users == null){
            throw new Exception("User exists");
        }
        User newUser = user;
        repository.createUser(newUser);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(path = "/{id}" , method = RequestMethod.DELETE)
    public ResponseEntity<User> removeUser(@PathVariable String id){
        User user = repository.getUserById(id);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        repository.removeUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }





}
