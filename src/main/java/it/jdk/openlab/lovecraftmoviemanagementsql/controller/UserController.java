package it.jdk.openlab.lovecraftmoviemanagementsql.controller;

import it.jdk.openlab.lovecraftmoviemanagementsql.$exception.SuperException;
import it.jdk.openlab.lovecraftmoviemanagementsql.aspects.ToLog;
import it.jdk.openlab.lovecraftmoviemanagementsql.models.User;
import it.jdk.openlab.lovecraftmoviemanagementsql.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static it.jdk.openlab.lovecraftmoviemanagementsql.aspects.LoggingAspect.LOGGER_LEVEL;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ToLog(level = LOGGER_LEVEL)
    @PostMapping(value = "/registration",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> insertUser(@RequestBody User user) throws SuperException {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.insertUser(user));
    }

    @ToLog(level = LOGGER_LEVEL)
    @PutMapping(value = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@RequestBody User user) throws SuperException {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.updateUser(user));
    }

    @ToLog(level = LOGGER_LEVEL)
    @DeleteMapping(value = "/delete",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> deleteUser(@RequestBody User user) throws SuperException {
        this.userService.deleteUser(user);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ToLog(level = LOGGER_LEVEL)
    @GetMapping(value = "/details/{username}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> user = this.userService.showUserByUsername(username);
        if(user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(user.get());
        }
    }
}
