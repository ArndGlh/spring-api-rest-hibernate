package arnaudg.persistence.service;

import arnaudg.persistence.dto.ProgressDto;
import arnaudg.persistence.models.Game;
import arnaudg.persistence.models.Progress;
import arnaudg.persistence.models.Task;
import arnaudg.persistence.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProgressService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TaskService taskService; // TODO pas beau

    @Autowired
    private UserService userService; // TODO pas beau

    /**
     * Save the progress in the database.
     */
    public void create(ProgressDto progressDto) {
        Task task = taskService.getById(progressDto.getTaskId());
        User user = userService.getById(progressDto.getUserId());
        Progress progress = new Progress(task, user, progressDto.getActual_progress(), progressDto.getMax_progress());

        entityManager.persist(progress);
    }

    @SuppressWarnings("unchecked")
    public List<Progress> findAll() {
        return entityManager.createQuery("from Progress").getResultList();
    }

    public Progress getById(int id) {
        return entityManager.find(Progress.class, id);
    }

    public List getByGame(int game) {
        return entityManager.createQuery("from Progress where game_id = :game")
                .setParameter("game", game)
                .getResultList();
    }

    public void save(ProgressDto progressDto) {
        Progress progress = entityManager.find(Progress.class, progressDto.getId());
        Task task = taskService.getById(progressDto.getTaskId());
        User user = userService.getById(progressDto.getUserId());

        progress.setActual_progress(progressDto.getActual_progress());
        progress.setMax_progress(progressDto.getMax_progress());
        progress.setTask(task);
        progress.setUser(user);
        entityManager.merge(progress);
    }


    /**
     * Delete the progress from the database.
     */
    public void delete(Progress progress) {
        if (entityManager.contains(progress)) {
            entityManager.remove(progress);
        } else {
            entityManager.remove(entityManager.merge(progress));
        }
    }
}