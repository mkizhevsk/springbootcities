package com.mk.webservices.service;

import com.mk.webservices.data.entity.City;
import com.mk.webservices.data.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CitiesService {

    private CityRepository cityRepository;

    @Autowired
    public CitiesService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> matchCities(String searchedName) {

        List<City> cities = cityRepository.findByName(searchedName);

        return cities;
    }
}
