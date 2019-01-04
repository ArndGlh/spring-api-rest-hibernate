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
import java.util.ArrayList;
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
    public boolean create(int userId, int gameId) {
        List tasks = taskService.getByGame(gameId);
        User user = userService.getById(userId);

        for(Object task : tasks){
            Task t = (Task) task;
            Progress progress = new Progress(t, user, 0, t.getMax_progess());
            entityManager.persist(progress);
        }
        return getByUserAndGame(userId,gameId).size() == tasks.size();
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

    public List getByUserAndGame(int userId, int gameId){
        return entityManager.createQuery("from Progress where user_id = :userId and task_id in (select id from Task where game_id = :gameId) order by task_id")
                .setParameter("gameId", gameId)
                .setParameter("userId", userId)
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