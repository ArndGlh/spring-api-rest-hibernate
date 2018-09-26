package arnaudg.persistence.service;

import arnaudg.persistence.dto.ProgressDto;
import arnaudg.persistence.models.Game;
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

    @Autowired
    private GameService gameService; // TODO pas beau

    @Autowired
    private UserService userService; // TODO pas beau

    /**
     * Save the progress in the database.
     */
    public void create(ProgressDto progressDto) {
        Game game = gameService.getById(progressDto.getGameId());
        User user = userService.getById(progressDto.getUserId());
        Progress progress = new Progress(game, user, progressDto.getCompletion());

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
        Game game = gameService.getById(progressDto.getGameId());
        User user = userService.getById(progressDto.getUserId());

        progress.setCompletion(progressDto.getCompletion());
        progress.setGame(game);
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