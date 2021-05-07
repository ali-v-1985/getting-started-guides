package me.valizadeh.example.optaplanner.persistence;

import me.valizadeh.example.optaplanner.domain.City;
import me.valizadeh.example.optaplanner.domain.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PathRepository {

    // There is only one path, so there is only pathId (= problemId).
    public static final Long SINGLETON_PATH_ID = 1L;

    @Autowired
    private CityRepository cityRepository;

    public Path findById(Long id) {
        if (!SINGLETON_PATH_ID.equals(id)) {
            throw new IllegalStateException("There is no path with id (" + id + ").");
        }
        // Occurs in a single transaction, so each initialized lesson references the same timeslot/room instance
        // that is contained by the timeTable's timeslotList/roomList.
        return new Path(cityRepository.findAll());
    }

    public void save(Path path) {
        for (City city : path.getCityList()) {
            // TODO this is awfully naive: optimistic locking causes issues if called by the SolverManager
            cityRepository.save(city);
        }
    }

}
