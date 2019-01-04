package arnaudg.web.controllers;

import arnaudg.persistence.dto.ProgressDto;
import arnaudg.persistence.models.Progress;
import arnaudg.persistence.service.ProgressService;
import arnaudg.web.util.RestPreconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.ws.rs.core.Response;
import java.util.List;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value = "/progress")
@CrossOrigin(origins = "*")
public class ProgressController {

    @Autowired
    private ProgressService progressService;

    /**
     * Get all the progress.
     */
    @RequestMapping(method = GET)
    @ResponseBody
    public List findAll() {
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

    /**
     * Get progress by user and game.
     */
    @RequestMapping(method = GET, value = "/user/{user_id}/{game_id}")
    @ResponseBody
    public List getByUserAndGame(@PathVariable("user_id") int userId, @PathVariable("game_id") int gameId) {
        return RestPreconditions.checkFound(progressService.getByUserAndGame(userId, gameId));
    }

    /**
     * Create a new progress
     * TODO change return type boolean
     */
    @RequestMapping(method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public boolean createProgress(@RequestBody int userId, int gameId) {
        RestPreconditions.checkFound(userId);
        RestPreconditions.checkFound(gameId);
        return progressService.create(userId, gameId);
    }

    /**
     * Update one progress
     */
    @RequestMapping(method = PUT)
    public Response updateProgress(@Valid @RequestBody ProgressDto progressRequestDto) {
        RestPreconditions.checkFound(progressRequestDto);
        progressService.save(progressRequestDto);

        return Response.status(Response.Status.OK).build();
    }

    /**
     * Delete one progress
     */
    @RequestMapping(method = DELETE, value = "/{progressId}")
    public Response deleteProgress(@PathVariable int progressId) {
        Progress progress = progressService.getById(RestPreconditions.checkFound(progressId));
        progressService.delete(progress);

        return Response.status(Response.Status.OK).build();
    }
}
