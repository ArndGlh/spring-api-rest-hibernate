package arnaudg.web.controllers;

import arnaudg.persistence.dto.TaskDto;
import arnaudg.persistence.models.Task;
import arnaudg.persistence.service.TaskService;
import arnaudg.web.util.RestPreconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value = "/task")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * Get all the task.
     */
    @RequestMapping(method = GET)
    @ResponseBody
    public List findAll() {
        return taskService.findAll();
    }

    /**
     * Get one task by id.
     */
    @RequestMapping(method = GET, value = "/id/{id}")
    @ResponseBody
    public Task findById(@PathVariable("id") int id) {
        return RestPreconditions.checkFound(taskService.getById(id));
    }

    /**
     * Get task by game.
     */
    @RequestMapping(method = GET, value = "/game/{gameId}")
    @ResponseBody
    public List findByGame(@PathVariable("gameId") int gameId) {
        return RestPreconditions.checkFound(taskService.getByGame(gameId));
    }

    /**
     * Create a new task
     */
    @RequestMapping(method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void createTask(@RequestBody TaskDto taskDto) {
        RestPreconditions.checkFound(taskDto);
        taskService.create(taskDto);
    }

    /**
     * Update one task
     */
    @RequestMapping(method = PUT)
    public void updateTask(@Valid @RequestBody TaskDto taskRequestDto) {
        RestPreconditions.checkFound(taskRequestDto);
        taskService.save(taskRequestDto);
    }

    /**
     * Delete one task
     */
    @RequestMapping(method = DELETE, value = "/{taskId}")
    public void deleteTask(@PathVariable int taskId) {
        Task task = taskService.getById(RestPreconditions.checkFound(taskId));
        taskService.delete(task);
    }
}
