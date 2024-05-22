package com.Icwd.user.service.controller;

import com.Icwd.user.service.entities.User;
import com.Icwd.user.service.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }
// get singal user

    int retryCount=1;
    @GetMapping("/{userId}")
   // @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
   @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
  //  @RateLimiter(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")// this for use jmeter software dowloade krna pdega 
    public  ResponseEntity<User> getSingalUser(@PathVariable String userId){

        logger.info("Retry count: {}", retryCount);
        retryCount++;

                     User user=userService.getUser(userId);

                     return ResponseEntity.ok(user);

    }
//crearing fallBack method for ciecuitbracker

    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
       // logger.info("Fallback is executed because service is down : ", ex.getMessage());

        ex.printStackTrace();

        User user = User.builder().email("dummy@gmail.com").name("Dummy").about("This user is created dummy because some service is down").userId("141234").build();
        return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUser =userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }

}
