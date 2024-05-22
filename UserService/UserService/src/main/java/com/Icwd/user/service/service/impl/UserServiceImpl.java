package com.Icwd.user.service.service.impl;

import com.Icwd.user.service.entities.Hotel;
import com.Icwd.user.service.entities.Rating;
import com.Icwd.user.service.entities.User;
import com.Icwd.user.service.exception.ResourceNotFoundException;
import com.Icwd.user.service.external.services.HotelService;
import com.Icwd.user.service.repositories.UserRepositories;
import com.Icwd.user.service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositories userRepositories;


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public HotelService hotelService;
    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public User saveUser(User user) {
        //genrate unique Id
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId (randomUserId);
        return userRepositories.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepositories.findAll();
    }




    //get singal user
    @Override
    public User getUser(String userId) {
        //get user from database with the help of user repository
       User user= userRepositories.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! : " + userId));
//fetch rating of the above user from RATING SERVICE
//http://localhost:8084/ratings/users/957f732b-a97e-4bf6-9e39-1a8b9caa973b

        Rating[] ratingsOfUser=restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+ user.getUserId() , Rating[].class);
        logger.info("{}", ratingsOfUser);

        List<Rating> ratings= Arrays.stream(ratingsOfUser).toList();

       List<Rating> ratingList= ratings.stream().map(rating -> {

          //api call to hotel Service to get the hotel
            //http://localhost:8083/hotels/2ab9e7a9-9fcc-4eb3-b7cb-c58c1642534e
     // ResponseEntity<Hotel> forEntity= restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
              Hotel hotel = hotelService.getHotel(rating.getHotelId());
      // logger.info("response status code: {}",forEntity.getStatusCode());
      //set the hotel to rating
          rating.setHotel(hotel);
          //return the rating
            return rating;
      }).collect(Collectors.toList());


        user.setRatings(ratingList);

        return user;
    }
}
