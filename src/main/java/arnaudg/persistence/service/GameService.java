package arnaudg.persistence.service;

import arnaudg.persistence.models.Game;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.persister.entity.AbstractEntityPersister;
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
    public List<Game> findAll(){
        return entityManager.createQuery("from Game").getResultList();
    }

    public List findAllAndProgress() {
        return entityManager.createQuery("from Game g, Progress p where g.id = p.game.id and p.user.id = 2") // TODO user id en dur a changer
                .getResultList();
    }

    public Game getById(int id){
        return entityManager.find(Game.class, id);
    }

    public List getByName(String name) {
        return entityManager.createQuery("from Game where title like :name")
                .setParameter("name", "%"+name+"%")
                .getResultList();
    }

    public List getByGenre(String genre) {
        return entityManager.createQuery("from Game where genre like :genre")
                .setParameter("genre", "%"+genre+"%")
                .getResultList();
    }

    public void save(Game game) {
        entityManager.merge(game);
    }

}