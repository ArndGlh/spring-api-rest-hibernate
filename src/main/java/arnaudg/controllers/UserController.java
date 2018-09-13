package arnaudg.controllers;

import arnaudg.models.User;
import arnaudg.models.dao.UserDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Class UserController
 */
@Controller
public class UserController {

    // TODO return JSON values

    @Autowired
    private UserDAO userDao;

    /**
     * Create a new user with an auto-generated id and email and name as passed
     * values.
     */
    @RequestMapping(value = "/create")
    @ResponseBody
    public String create(String email, String name) {
        try {
            User user = new User(email, name);
            userDao.create(user);
        } catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User succesfully created!";
    }

    /**
     * Delete the user with the passed id.
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(long id) {
        try {
            User user = new User(id);
            userDao.delete(user);
        } catch (Exception ex) {
            return "Error deleting the user: " + ex.toString();
        }
        return "User succesfully deleted!";
    }

    /**
     * Get all the users.
     */
    @RequestMapping(value = "/users")
    @ResponseBody
    public List<User> findAll() {
        try {
            return userDao.getAll();
        } catch (Exception ex) {
            System.err.println("Users not found: " + ex.toString()); // TODO Logger
        }
        return null;
    }

    /**
     * Retrieve the id for the user with the passed email address.
     */
    @RequestMapping(value = "/get-by-email")
    @ResponseBody
    public String getByEmail(String email) {
        String userId;
        try {
            User user = userDao.getByEmail(email);
            userId = String.valueOf(user.getId());
        } catch (Exception ex) {
            return "User not found: " + ex.toString();
        }
        return "The user id is: " + userId;
    }

    /**
     * Update the email and the name for the user indentified by the passed id.
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public String updateName(long id, String email, String name) {
        try {
            User user = userDao.getById(id);
            user.setEmail(email);
            user.setName(name);
            userDao.update(user);
        } catch (Exception ex) {
            return "Error updating the user: " + ex.toString();
        }
        return "User succesfully updated!";
    }
}