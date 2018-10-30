package com.mk.webservices.data.repository;

import com.mk.webservices.data.entity.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<City, Integer> {

    @Query("SELECT c FROM City c WHERE c.name LIKE %?1%")
    List<City> findByName(String letters);

}
