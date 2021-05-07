package me.valizadeh.example.optaplanner.solver;

import com.example.persistence.TimeTableRepository;
import me.valizadeh.example.optaplanner.domain.Path;
import me.valizadeh.example.optaplanner.persistence.PathRepository;
import org.optaplanner.core.api.score.ScoreManager;
import org.optaplanner.core.api.solver.SolverManager;
import org.optaplanner.core.api.solver.SolverStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/path")
public class PathController {

    @Autowired
    private PathRepository pathRepository;
    @Autowired
    private SolverManager<Path, Long> solverManager;
    @Autowired
    private ScoreManager<Path> scoreManager;

    // To try, GET http://localhost:8080/path
    @GetMapping()
    public Path getPath() {
        Path path = pathRepository.findById(PathRepository.SINGLETON_PATH_ID);
        scoreManager.updateScore(path); // Sets the score
        path.setSolverStatus(getSolverStatus());
        return path;
    }

    @PostMapping("/solve")
    public void solve() {
        solverManager.solveAndListen(TimeTableRepository.SINGLETON_TIME_TABLE_ID,
                pathRepository::findById,
                pathRepository::save);
    }

    public SolverStatus getSolverStatus() {
        return solverManager.getSolverStatus(TimeTableRepository.SINGLETON_TIME_TABLE_ID);
    }

    @PostMapping("/stopSolving")
    public void stopSolving() {
        solverManager.terminateEarly(TimeTableRepository.SINGLETON_TIME_TABLE_ID);
    }

}
