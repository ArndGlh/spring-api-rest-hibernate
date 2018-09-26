package arnaudg.persistence.service;

import arnaudg.persistence.models.Task;
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

    /**
     * Save the task in the database.
     */
    public void create(Task task) {
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
    public void save(Task task) {
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
    public List getByProgress(int progressId, int userId) {
        return entityManager.createQuery("from Task t, Progress p where p.id = t.progress_id and p.id = :progressId and p.user_id = :userId")
                .setParameter(":progressId", progressId)
                .setParameter("userId", userId)
                .getResultList();
    }
}






























