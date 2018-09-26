package arnaudg.web.controllers;

import arnaudg.persistence.dto.ProgressDto;
import arnaudg.persistence.models.Game;
import arnaudg.persistence.models.Progress;
import arnaudg.persistence.models.User;
import arnaudg.persistence.service.ProgressService;
import arnaudg.web.util.RestPreconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value = "/progress")
public class ProgressController {

    @Autowired
    private ProgressService progressService;

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

    /**
     * Create a new progress
     */
    @RequestMapping(method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void createProgress(@RequestBody ProgressDto progressDto) {
        RestPreconditions.checkFound(progressDto);
        progressService.create(progressDto);
    }

    /**
     * Update one progress
     */
    @RequestMapping(method = PUT)
    public void updateProgress(@Valid @RequestBody ProgressDto progressRequestDto) {
        RestPreconditions.checkFound(progressRequestDto);
        progressService.save(progressRequestDto);
    }

    /**
     * Delete one progress
     */
    @RequestMapping(method = DELETE, value = "/{progressId}")
    public void deleteProgress(@PathVariable int progressId) {
        Progress progress = progressService.getById(RestPreconditions.checkFound(progressId));
        progressService.delete(progress);
    }
}
