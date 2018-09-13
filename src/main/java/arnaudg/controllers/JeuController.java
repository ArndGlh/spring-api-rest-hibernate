package arnaudg.controllers;

import arnaudg.models.Jeu;
import arnaudg.models.dao.JeuDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value = "/jeux")
public class JeuController {

    @Autowired
    private JeuDAO jeuDAO;

    /**
     * Get all the games.
     */
    @RequestMapping(method=GET, value = "/")
    @ResponseBody
    public List<Jeu> findAll() {
        try {
            return jeuDAO.getAll();
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
    public Jeu findById(@PathVariable("id") int id) {
        try {
            return jeuDAO.getById(id);
        } catch (Exception ex) {
            System.err.println("Jeu not found: " + ex.toString()); // TODO Logger
        }
        return null;
    }
}
