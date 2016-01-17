package gr.etherasTickets.user;

import gr.etherasTickets.dto.AuthDto;
import gr.etherasTickets.exceptions.RestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired // new object on demand (dependency injection)
    private UserRepository repository;

    @RequestMapping(path = "/{id}" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable String id) throws RestException{
        return new ResponseEntity<>(repository.getUserById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/auth" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthDto> authorize(@RequestParam Map<String,String> requestParams) throws Exception{
        String username=requestParams.get("username");
        String password=requestParams.get("password");
        String response = repository.userLogin(username,password);
        AuthDto authDto = new AuthDto(response);

        return new ResponseEntity<>(authDto, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody User user) throws Exception{
        User users = repository.findByUsername(user.getUsername());
        if(users != null){
            throw new Exception("User exists");
        }
        User newUser = user;
        repository.createUser(newUser);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(path = "/{id}" , method = RequestMethod.DELETE)
    public ResponseEntity<User> removeUser(@PathVariable String id) throws RestException{
        User user = repository.getUserById(id);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        repository.removeUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(path = "/{id}" , method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user){


        repository.updateUser(id, user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
