package arnaudg.web.controllers;

import arnaudg.persistence.models.Game;
import arnaudg.persistence.repository.GameRepository;
import arnaudg.persistence.service.GameService;
import arnaudg.web.exception.GameNotFoundException;
import arnaudg.web.util.RestPreconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value = "/game")
public class GameController {

    @Autowired
    private GameService gameService;

    /**
     * Get all the games.
     */
    @RequestMapping(method = GET)
    @ResponseBody
    public List<Game> findAll() {
        return gameService.findAll();
    }

    /**
     * Get one game by id.
     */
    @RequestMapping(method = GET, value = "/id/{id}")
    @ResponseBody
    public Game findById(@PathVariable("id") int id) {
        return RestPreconditions.checkFound(gameService.getById(id));
    }

    /**
     * Get games by title.
     */
    @RequestMapping(method = GET, value = "/name/{name}")
    @ResponseBody
    public List findByName(@PathVariable("name") String name) {
        return RestPreconditions.checkFound(gameService.getByName(name));
    }

    /**
     * Get games by genre.
     */
    @RequestMapping(method = GET, value = "/genre/{genre}")
    @ResponseBody
    public List findByGenre(@PathVariable("genre") String genre) {
        return RestPreconditions.checkFound(gameService.getByGenre(genre));
    }

    /**
     * Create one game.
     */
    @RequestMapping(method = POST)
    public void createGame(@Valid @RequestBody Game game) {
        RestPreconditions.checkFound(game);
        gameService.create(game);
    }

    /**
     * Update one game
     */
    @RequestMapping(method = PUT, value = "/{gameId}")
    public void updateGame(@PathVariable int gameId, @Valid @RequestBody Game gameRequest) {
        RestPreconditions.checkFound(gameRequest);
        Game game = gameService.getById(RestPreconditions.checkFound(gameId));
        game.setTitle(gameRequest.getTitle());
        game.setGenre(gameRequest.getGenre());
        game.setYear(gameRequest.getYear());

        gameService.save(game);
    }

    /**
     * Delete one game
     */
    @RequestMapping(method = DELETE, value = "/{gameId}")
    public ResponseEntity<?> deleteGame(@PathVariable int gameId) {
        RestPreconditions.checkFound(gameRequest);
        Game game = gameService.getById(RestPreconditions.checkFound(gameId));
        gameService.delete(game);
    }
}
