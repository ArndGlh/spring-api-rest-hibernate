package arnaudg.web.controllers;

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
public class TaskController {

    @Autowired
    private TaskService taskService;

    /**
     * Get all the task.
     */
    @RequestMapping(method = GET)
    @ResponseBody
    public List<Task> findAll() {
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
     * Get task by progress.
     */
    @RequestMapping(method = GET, value = "/progress/{userId}/{progressId}")
    @ResponseBody
    public List findByProgress(@PathVariable("userId") int userId, @PathVariable("progressId") int progressId) {
        return RestPreconditions.checkFound(taskService.getByProgress(progressId, userId));
    }

    /**
     * Create a new task
     */
    @RequestMapping(method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void createTask(@RequestBody Task task) {
        RestPreconditions.checkFound(task);
        taskService.create(task);
    }

    /**
     * Update one task
     */
    @RequestMapping(method = PUT)
    public void updateTask(@Valid @RequestBody Task taskRequest) {
        RestPreconditions.checkFound(taskRequest);
        Task task = taskService.getById(taskRequest.getId());
        task.setId(taskRequest.getId());
        task.setName(taskRequest.getName());
        task.setDescription(taskRequest.getDescription());
        task.setMax_progress(taskRequest.getMax_progress());
        task.setActual_progress(taskRequest.getActual_progress());

        taskService.save(task);
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
