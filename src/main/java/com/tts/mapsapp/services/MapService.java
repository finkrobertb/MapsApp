package com.tts.mapsapp.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tts.mapsapp.deserialization.GeocodingResponse;
import com.tts.mapsapp.deserialization.GeocodingReverseResponse;
import com.tts.mapsapp.deserialization.Location;

@Service
public class MapService
{
    @Value("${api_key}")
    private String apiKey;

    public void addCoordinates(Location location)
    {
        // URL to look up location + the city location + city state + get API key +
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + location.getCity() + "," + location.getState() + "&key=" + apiKey;

        // Used to make a web call
        RestTemplate restTemplate = new RestTemplate();
        // Get REST response and store it in GeocodingResponse
        GeocodingResponse response = restTemplate.getForObject(url, GeocodingResponse.class);
        // get results/location from response and store it in coordinates
        Location coordinates = response.getResults().get(0).getGeometry().getLocation();
        // Set location's lat & long to that of the coordinates
        location.setLat(coordinates.getLat());
        location.setLng(coordinates.getLng());
    }

    public void addPlace(Location location)
    {
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + location.getLat() + "," + location.getLng() + "&key=" + apiKey;

        RestTemplate restTemplate = new RestTemplate();

        GeocodingReverseResponse response = restTemplate.getForObject(url, GeocodingReverseResponse.class);

        if(response.getResults().size() == 0)
        {
            location.setCity("Unknown");
        }
        else
        {
            String formattedAddress = response.getResults().get(0).getFormatted_address();
            location.setCity(formattedAddress);
        }
    }

    // Previous version of random before in class update
    // // Generates random Coordinates
    // public void randomCoordinates(Location location)
    // {
    // // Long & Lat min and max
    // int latMin = -90;
    // int latMax = 90;
    // int lngMin = -180;
    // int lngMax = 180;
    //
    // // Computes randomly and converts to string
    // int randomLatInt = (int) Math.floor(Math.random() * (latMax - latMin + 1) +
    // latMin);
    // int randomLngInt = (int) Math.floor(Math.random() * (lngMax - lngMin + 1) +
    // lngMin);
    // String randomLat = Integer.toString(randomLatInt);
    // String randomLng = Integer.toString(randomLngInt);
    //
    // // Sets location's Lat/Lng to randomLat/Lng
    // location.setLat(randomLat);
    // location.setLng(randomLng);
    // }

}