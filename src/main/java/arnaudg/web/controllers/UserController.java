package arnaudg.web.controllers;

import arnaudg.persistence.models.User;
import arnaudg.persistence.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Class UserController
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    // TODO return JSON values
    // TODO erreur de fetch

    @Autowired
    private UserService userDao;

    /**
     * Get all the users.
     */
    @RequestMapping(method=GET, value = "/findAll")
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
     * Get one user by its id.
     */
    @RequestMapping(method=GET, value = "/{id}")
    @ResponseBody
    public User findAll(@PathVariable("id") int id) {
        try {
            return userDao.getById(id);
        } catch (Exception ex) {
            System.err.println("User not found: " + ex.toString()); // TODO Logger
        }
        return null;
    }

    /**
     * Create a new user with an auto-generated id and email and name as passed
     * values.
     */
    @RequestMapping(method=POST, value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public String create(@RequestBody User user) {
        try {
            userDao.create(user);
        } catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User succesfully created!";
    }

    /**
     * Delete the user with the passed id.
     */
    @RequestMapping(method=DELETE, value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String delete(@PathVariable("id") long id) {
        try {
            User user = new User(id);
            userDao.delete(user);
        } catch (Exception ex) {
            return "Error deleting the user: " + ex.toString();
        }
        return "User succesfully deleted!";
    }

//    /**
//     * Retrieve the id for the user with the passed email address.
//     */
//    @RequestMapping(method=GET, value = "/get-by-email")
//    @ResponseBody
//    public String getByEmail(String email) {
//        String userId;
//        try {
//            User user = userDao.getByEmail(email);
//            userId = String.valueOf(user.getId());
//        } catch (Exception ex) {
//            return "User not found: " + ex.toString();
//        }
//        return "The user id is: " + userId;
//    }

    /**
     * Update the email and the name for the user indentified by the passed id.
     */
    @RequestMapping(method=PUT, value = "/update")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String updateName(long id, String email, String name, String password) {
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