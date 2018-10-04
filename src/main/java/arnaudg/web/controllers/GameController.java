package arnaudg.web.controllers;

import arnaudg.persistence.models.Game;
import arnaudg.persistence.service.GameService;
import arnaudg.web.util.RestPreconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.core.Response;

import java.util.List;

import static com.google.common.net.HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value = "/game")
@CrossOrigin(origins = "*")
public class GameController {

    @Autowired
    private GameService gameService;

    /**
     * Get all the games.
     */
    @RequestMapping(method = GET)
    @ResponseBody
    public List findAll() {
        return gameService.findAll();
    }

    /**
     * Get all the games and progress associated
     */
    @RequestMapping(method = GET, value = "/progress")
    @ResponseBody
    public List findAllAndProgress() {
        return gameService.findAllAndProgress();
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
        return gameService.getByName(name);
    }

    /**
     * Get games by genre.
     */
    @RequestMapping(method = GET, value = "/genre/{genre}")
    @ResponseBody
    public Response findByGenre(@PathVariable("genre") String genre) {
        return Response.status(Response.Status.OK)
                .entity(RestPreconditions.checkFound(gameService.getByGenre(genre)))
                .header(ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                .build();
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
    @RequestMapping(method = PUT)
    public void updateGame(@Valid @RequestBody Game gameRequest) {
        RestPreconditions.checkFound(gameRequest);
        Game game = gameService.getById(RestPreconditions.checkFound(gameRequest.getId()));
        game.setTitle(gameRequest.getTitle());
        game.setGenre(gameRequest.getGenre());
        game.setYear(gameRequest.getYear());

        gameService.save(game);
    }

    /**
     * Delete one game
     */
    @RequestMapping(method = DELETE, value = "/{gameId}")
    public void deleteGame(@PathVariable int gameId) {
        Game game = gameService.getById(RestPreconditions.checkFound(gameId));
        gameService.delete(game);
    }
}
