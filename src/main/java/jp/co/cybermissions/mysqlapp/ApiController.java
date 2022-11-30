package jp.co.cybermissions.mysqlapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ApiController
 */
@RestController
@RequestMapping(path = "/api")
public class ApiController {
   
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/users")
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public User getUser(@PathVariable(value = "id") Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @PostMapping(path = "/users")
    public User createUser(@RequestBody User user) {
        User u = new User();
        u.setName(user.getName());
        u.setEmail(user.getEmail());
        return userRepository.save(u);
    }

    @PutMapping(path = "/users/{id}")
    public User updateUser(
            @PathVariable(value = "id") Integer id,
            @RequestBody User user) {
        User u = userRepository.findById(id).orElse(null);
        if(u == null) 
            return null;
        u.setName(user.getName());
        u.setEmail(user.getEmail());
        return userRepository.save(u);    
    }

    @DeleteMapping(path = "/users/{id}")
    public void deleteUser(@PathVariable(value = "id") Integer id) {
        userRepository.deleteById(id);
    }
}