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

import static com.google.common.net.HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN;
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
    public Response findAll() {
        return Response.status(Response.Status.OK)
                .entity(progressService.findAll())
                .build();
    }

    /**
     * Get one progress by id.
     */
    @RequestMapping(method = GET, value = "/id/{id}")
    @ResponseBody
    public Response findById(@PathVariable("id") int id) {
        return Response.status(Response.Status.OK)
                .entity(RestPreconditions.checkFound(progressService.getById(id)))
                .build();
    }

    /**
     * Get progress by game.
     */
    @RequestMapping(method = GET, value = "/game/{id}")
    @ResponseBody
    public Response findByGame(@PathVariable("id") int id) {
        return Response.status(Response.Status.OK)
                .entity(RestPreconditions.checkFound(progressService.getByGame(id)))
                .build();
    }

    /**
     * Create a new progress
     */
    @RequestMapping(method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Response createProgress(@RequestBody ProgressDto progressDto) {
        RestPreconditions.checkFound(progressDto);
        progressService.create(progressDto);
        return Response.status(Response.Status.CREATED).build();
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
