package arnaudg.service;

import arnaudg.models.Game;

import java.util.List;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class GameService {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Save the game in the database.
     */
    public void create(Game game) {
        entityManager.persist(game);
    }

    /**
     * Delete the game from the database.
     */
    public void delete(Game game) {
        if (entityManager.contains(game)) {
            entityManager.remove(game);
        } else {
            entityManager.remove(entityManager.merge(game));
        }
    }

    @SuppressWarnings("unchecked")
    public List<Game> getAll(){
        return entityManager.createQuery("from Game").getResultList();
    }

    public Game getById(int id){
        return entityManager.find(Game.class, id);
    }
}