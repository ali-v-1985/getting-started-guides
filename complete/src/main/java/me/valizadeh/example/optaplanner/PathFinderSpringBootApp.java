package me.valizadeh.example.optaplanner;

import me.valizadeh.example.optaplanner.domain.City;
import me.valizadeh.example.optaplanner.persistence.CityRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PathFinderSpringBootApp {

    public static void main(String[] args) {
        SpringApplication.run(PathFinderSpringBootApp.class, args);
    }

    @Value("${path.demoData:SMALL}")
    private DemoData demoData;

    @Bean
    public CommandLineRunner demoData(
            CityRepository cityRepository) {
        return (args) -> {
            if (demoData == DemoData.NONE) {
                return;
            }

            cityRepository.save(new City("Amsterdam", "52.3667° N", "4.8945° E"));
            cityRepository.save(new City("Rotterdam", "51.9244° N", "4.4777° E"));
            cityRepository.save(new City("Brussels", "50.8503° N", "4.3517° E"));
            cityRepository.save(new City("Paris", "48.8566° N", "2.3522° E"));
            cityRepository.save(new City("Bordeaux", "44.8378° N", "0.5792° W"));
            cityRepository.save(new City("Marseille", "43.2965° N", "5.3698° E"));
            cityRepository.save(new City("Madrid", "40.4168° N", "3.7038° W"));
            cityRepository.save(new City("Barcelona", "41.3851° N", "2.1734° E"));
            cityRepository.save(new City("Lisbon", "38.7223° N", "9.1393° W"));
            cityRepository.save(new City("Turin", "45.0703° N", "7.6869° E"));
            cityRepository.save(new City("Milan", "45.4642° N", "9.1900° E"));
            cityRepository.save(new City("Rome", "41.9028° N", "12.4964° E"));
            cityRepository.save(new City("Venice", "45.4408° N", "12.3155° E"));

        };
    }

    public enum DemoData {
        NONE,
        SMALL,
        LARGE
    }

}
