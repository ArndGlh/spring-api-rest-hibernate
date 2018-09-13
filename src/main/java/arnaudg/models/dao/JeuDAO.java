package arnaudg.models.dao;

import arnaudg.models.Jeu;
import arnaudg.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository
@Transactional
public class JeuDAO {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Save the jeu in the database.
     */
    public void create(Jeu jeu) {
        entityManager.persist(jeu);
    }

    /**
     * Delete the jeu from the database.
     */
    public void delete(Jeu jeu) {
        if (entityManager.contains(jeu)) {
            entityManager.remove(jeu);
        } else {
            entityManager.remove(entityManager.merge(jeu));
        }
    }

    @SuppressWarnings("unchecked")
    public List<Jeu> getAll(){
        return entityManager.createQuery("from Jeu").getResultList();
    }

    public Jeu getById(int id){
        return entityManager.find(Jeu.class, id);
    }
}