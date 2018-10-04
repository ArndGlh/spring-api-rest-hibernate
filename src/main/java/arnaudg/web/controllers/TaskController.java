package arnaudg.web.controllers;

import arnaudg.persistence.dto.TaskDto;
import arnaudg.persistence.models.Task;
import arnaudg.persistence.service.TaskService;
import arnaudg.web.util.RestPreconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.core.Response;

import static com.google.common.net.HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value = "/task")
//@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * Get all the task.
     */
    @RequestMapping(method = GET)
    @ResponseBody
    public Response findAll() {
        return Response.status(Response.Status.OK)
                .entity(taskService.findAll())
                .header(ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                .build();
    }

    /**
     * Get one task by id.
     */
    @RequestMapping(method = GET, value = "/id/{id}")
    @ResponseBody
    public Response findById(@PathVariable("id") int id) {
        return Response.status(Response.Status.OK)
                .entity(RestPreconditions.checkFound(taskService.getById(id)))
                .header(ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                .build();
    }

    /**
     * Get task by game.
     */
    @RequestMapping(method = GET, value = "/game/{userId}/{gameId}")
    @ResponseBody
    public Response findByProgress(@PathVariable("userId") int userId, @PathVariable("gameId") int gameId) {
        return Response.status(Response.Status.OK)
                .entity(RestPreconditions.checkFound(taskService.getByProgress(gameId, userId)))
                .header(ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                .build();
    }

    /**
     * Create a new task
     */
    @RequestMapping(method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Response createTask(@RequestBody TaskDto taskDto) {
        RestPreconditions.checkFound(taskDto);
        taskService.create(taskDto);

        return Response.status(Response.Status.CREATED).build();
    }

    /**
     * Update one task
     */
    @RequestMapping(method = PUT)
    public Response updateTask(@Valid @RequestBody TaskDto taskRequestDto) {
        RestPreconditions.checkFound(taskRequestDto);
        taskService.save(taskRequestDto);

        return Response.status(Response.Status.OK).build();
    }

    /**
     * Delete one task
     */
    @RequestMapping(method = DELETE, value = "/{taskId}")
    public Response deleteTask(@PathVariable int taskId) {
        Task task = taskService.getById(RestPreconditions.checkFound(taskId));
        taskService.delete(task);

        return Response.status(Response.Status.OK).build();
    }
}
