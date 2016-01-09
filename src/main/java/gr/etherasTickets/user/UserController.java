package gr.etherasTickets.user;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @RequestMapping(value = "/getUser" , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@RequestParam(name="id" , required=true) String id){
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
    

    @RequestMapping(value = "/new" , method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestParam Map<String,String> requestParams) throws Exception{
        ObjectId id = new ObjectId();
        String firstname=requestParams.get("firstname");
        String lastname=requestParams.get("lastname");
        String email=requestParams.get("email");
        String username=requestParams.get("username");
        String password=requestParams.get("password");

        repository.createUser(id.toString(),firstname,lastname,email,username,password, 0, new ArrayList<>());

        return new ResponseEntity<>(HttpStatus.OK);
    }





}
