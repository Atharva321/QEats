package com.crio.qeats.services;

import com.crio.qeats.dto.Restaurant;
import com.crio.qeats.exchanges.GetRestaurantsRequest;
import com.crio.qeats.exchanges.GetRestaurantsResponse;
import com.crio.qeats.repositoryservices.RestaurantRepositoryService;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.crio.qeats.repositoryservices.RestaurantRepositoryServiceDummyImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RestaurantServiceImpl implements RestaurantService {

  private final Double peakHoursServingRadiusInKms = 3.0;
  private final Double normalHoursServingRadiusInKms = 5.0;

  @Autowired
  private RestaurantRepositoryService restaurantRepositoryService;

  private GetRestaurantsResponse getRestaurantsResponse;


  // TODO: CRIO_TASK_MODULE_RESTAURANTSAPI - Implement findAllRestaurantsCloseby.
  // Check RestaurantService.java file for the interface contract.
  @Override
  public GetRestaurantsResponse findAllRestaurantsCloseBy(
          GetRestaurantsRequest getRestaurantsRequest, LocalTime currentTime) {
    // TODO: CRIO_TASK_MODULE_RESTAURANTSAPI - Implement findAllRestaurantsCloseby.
    // Check RestaurantService.java file for the interface contract.
    double lattitude = getRestaurantsRequest.getLatitude();
    double longitude = getRestaurantsRequest.getLongitude();
    List<Restaurant> restaurants = new ArrayList<>();
    getRestaurantsResponse = new GetRestaurantsResponse();

    // RestaurantRepositoryServiceDummyImpl restaurantRepositoryServiceDummy = new RestaurantRepositoryServiceDummyImpl();
    // Check if the current time falls within peak hours

    if ((currentTime.isAfter(LocalTime.of(7, 59)) && currentTime.isBefore(LocalTime.of(10, 01)))
            || (currentTime.isAfter(LocalTime.of(12, 59)) && currentTime.isBefore(LocalTime.of(14, 01)))
            || (currentTime.isAfter(LocalTime.of(18, 59)) && currentTime.isBefore(LocalTime.of(21, 01)))) {
      // Set serving radius to 3KMs
      restaurants = new ArrayList<>(restaurantRepositoryService.findAllRestaurantsCloseBy(lattitude, longitude, currentTime, peakHoursServingRadiusInKms));
      // if(restaurants.size() < 10){
      // //   getRestaurantsResponse.setRestaurants
      // restaurants = new ArrayList<>(restaurantRepositoryServiceDummy.findAllRestaurantsCloseBy(lattitude, longitude, currentTime, normalHoursServingRadiusInKms));
      // }
    } else {
      // Set serving radius to 5KMs
      // getRestaurantsResponse.setRestaurants(
      restaurants = new ArrayList<>(restaurantRepositoryService.findAllRestaurantsCloseBy(lattitude, longitude, currentTime, normalHoursServingRadiusInKms));
    }

    getRestaurantsResponse.setRestaurants(restaurants);

    // System.out.println(getRestaurantsResponse.getRestaurants());


    return getRestaurantsResponse;


  }


}

