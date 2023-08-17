package com.crio.qeats.exchanges;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.crio.qeats.dto.Restaurant;
import lombok.*;

// TODO: CRIO_TASK_MODULE_RESTAURANTSAPI
//  Implement GetRestaurantsRequest.
//  Complete the class such that it is able to deserialize the incoming query params from
//  REST API clients.
//  For instance, if a REST client calls API
//  /qeats/v1/restaurants?latitude=28.4900591&longitude=77.536386&searchFor=tamil,
//  this class should be able to deserialize lat/long and optional searchFor from that.
@Data
@NoArgsConstructor
public class GetRestaurantsRequest {
    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    private String searchFor;
    public void setSearchFor(String searchFor) {
        this.searchFor = searchFor;
    }
    public GetRestaurantsRequest(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public GetRestaurantsRequest(Double latitude, Double longitude, String searchFor) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.searchFor = searchFor;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getSearchFor() {
        return searchFor;
    }
}

