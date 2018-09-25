package arnaudg.web.controllers;

import arnaudg.persistence.models.Game;
import arnaudg.persistence.service.GameService;
import arnaudg.web.util.RestPreconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

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
     * Get games by name.
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
}
