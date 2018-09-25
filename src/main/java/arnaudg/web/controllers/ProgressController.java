package arnaudg.web.controllers;

import arnaudg.persistence.models.Progress;
import arnaudg.persistence.models.User;
import arnaudg.persistence.service.ProgressService;
import arnaudg.web.util.RestPreconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/progress")
public class ProgressController {

    @Autowired
    private ProgressService progressService;

    // ---------
    // -- GET --
    // ---------

    /**
     * Get all the progress.
     */
    @RequestMapping(method = GET)
    @ResponseBody
    public List<Progress> findAll() {
        return progressService.findAll();
    }

    /**
     * Get one progress by id.
     */
    @RequestMapping(method = GET, value = "/id/{id}")
    @ResponseBody
    public Progress findById(@PathVariable("id") int id) {
        return RestPreconditions.checkFound(progressService.getById(id));
    }

    /**
     * Get progress by game.
     */
    @RequestMapping(method = GET, value = "/game/{id}")
    @ResponseBody
    public List findByGame(@PathVariable("id") int id) {
        return RestPreconditions.checkFound(progressService.getByGame(id));
    }

    // --------
    // - POST -
    // --------

    /**
     * Create a new user with an auto-generated id and email and name as passed
     * values.
     */
    @RequestMapping(method = POST, value = "/create/{gameId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void create(@RequestBody Progress progress, @PathVariable("gameId") int id) {
        RestPreconditions.checkFound(progress);
        progressService.create(progress);

    }
}
