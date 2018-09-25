package arnaudg.persistence.service;

import arnaudg.persistence.models.Progress;
import arnaudg.persistence.models.User;
import arnaudg.web.util.RestPreconditions;
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

    /**
     * Save the progress in the database.
     */
    public void create(Progress progress) {
        entityManager.persist(progress);
    }

    /**
     * Delete the progress from the database.
     */
//    public void delete(Progress progress) {
//        if (entityManager.contains(progress)) {
//            entityManager.remove(progress);
//        } else {
//            entityManager.remove(entityManager.merge(progress));
//        }
//    }
    @SuppressWarnings("unchecked")
    public List<Progress> findAll() {
        return entityManager.createQuery("from Progress").getResultList();
    }

    public Progress getById(int id) {
        return entityManager.find(Progress.class, id);
    }

    public List getByGame(int game) {
        return entityManager.createQuery("from Progress where id_game = :game")
                .setParameter("game", game)
                .getResultList();
    }
}