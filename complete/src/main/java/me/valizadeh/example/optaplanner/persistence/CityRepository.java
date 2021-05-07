package me.valizadeh.example.optaplanner.persistence;

import me.valizadeh.example.optaplanner.domain.City;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CityRepository extends PagingAndSortingRepository<City, Long> {

    @Override
    List<City> findAll();

}
