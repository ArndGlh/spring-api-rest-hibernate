package arnaudg.persistence.service;

import arnaudg.persistence.dto.TaskDto;
import arnaudg.persistence.models.Progress;
import arnaudg.persistence.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TaskService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ProgressService progressService;

    /**
     * Save the task in the database.
     */
    public void create(TaskDto taskDto) {
        Progress progress = progressService.getById(taskDto.getProgressId());
        Task task = new Task(progress,
                taskDto.getName(),
                taskDto.getDescription(),
                taskDto.getActualProgress(),
                taskDto.getMaxProgress());
        entityManager.persist(task);
    }

    /**
     * Delete the task from the database.
     */
    public void delete(Task task) {
        if (entityManager.contains(task)) {
            entityManager.remove(task);
        } else {
            entityManager.remove(entityManager.merge(task));
        }
    }

    /**
     * Update the task in the database.
     */
    public void save(TaskDto taskDto) {
        Task task = entityManager.find(Task.class, taskDto.getId());
        
        task.setId(taskDto.getId());
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setMax_progress(taskDto.getMaxProgress());
        task.setActual_progress(taskDto.getActualProgress());

        entityManager.merge(task);
    }

    @SuppressWarnings("unchecked")
    public List<Task> findAll(){
        return entityManager.createQuery("from Task").getResultList();
    }

    public Task getById(int id){
        return entityManager.find(Task.class, id);
    }

    /**
     * Get all task by name
     *
     */
    public List getByName(String name) {
        return entityManager.createQuery("from Task where name like :name")
                .setParameter("name", "%"+name+"%")
                .getResultList();
    }

    /**
     * Get all task by progress and user
     *
     */
    public List getByProgress(int gameId, int userId) {
        return entityManager.createQuery("from Task where progress_id in (select id from Progress where user_id = :userId and game_id = :gameId)")
                .setParameter("gameId", gameId)
                .setParameter("userId", userId)
                .getResultList();

    }
}