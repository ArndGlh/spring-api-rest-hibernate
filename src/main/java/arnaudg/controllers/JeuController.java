package arnaudg.controllers;

import arnaudg.models.Jeu;
import arnaudg.models.dao.JeuDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JeuController {

    @Autowired
    private JeuDAO jeuDAO;

    /**
     * Get all the games.
     */
    @RequestMapping(value = "/jeux")
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
    @RequestMapping(value = "/jeux/{id}")
    @ResponseBody
    public List<Jeu> findById(@PathVariable("id") int id) {
        try {
            return jeuDAO.getById(id);
        } catch (Exception ex) {
            System.err.println("Jeu not found: " + ex.toString()); // TODO Logger
        }
        return null;
    }
}
