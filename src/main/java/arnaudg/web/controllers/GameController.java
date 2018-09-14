package arnaudg.web.controllers;

import arnaudg.persistence.models.Game;
import arnaudg.persistence.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/games")
public class GameController {

    @Autowired
    private GameService gameService;

    /**
     * Get all the games.
     */
    @RequestMapping(method=GET, value = "/findAll")
    @ResponseBody
    public List<Game> findAll() {
        try {
            return gameService.getAll();
        } catch (Exception ex) {
            System.err.println("Games not found: " + ex.toString()); // TODO Logger
        }
        return null;
    }

    /**
     * Get one game by id.
     */
    @RequestMapping(method=GET, value = "/{id}")
    @ResponseBody
    public Game findById(@PathVariable("id") int id) {
        try {
            return gameService.getById(id);
        } catch (Exception ex) {
            System.err.println("Game not found: " + ex.toString()); // TODO Logger
        }
        return null;
    }
}
