package com.mk.webservices.service;

import com.mk.webservices.data.entity.Country;
import com.mk.webservices.data.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CountriesService {
    private CountryRepository countryRepository;

    @Autowired
    public CountriesService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getCountries() {
        List<Country> companies = new ArrayList<>();
        countryRepository.findAll().forEach(company -> companies.add(company));
        return companies;
    }

    public void saveCountry(Country theCountry) {
        countryRepository.save(theCountry);
    }

    public Country getCountry(int theId) {
        Country country = countryRepository.findById(theId).get();
        return country;
    }

    public void deleteCountry(int theId) {
        countryRepository.deleteById(theId);
    }
}
