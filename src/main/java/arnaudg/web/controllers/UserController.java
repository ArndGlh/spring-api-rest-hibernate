package arnaudg.web.controllers;

import arnaudg.persistence.models.User;
import arnaudg.persistence.service.UserService;

import arnaudg.web.util.RestPreconditions;
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
    private UserService userService;

    /**
     * Get all the users.
     */
    @RequestMapping(method = GET)
    @ResponseBody
    public List<User> findAll() {
        return userService.findAll();
    }

    /**
     * Get one user by its id.
     */
    @RequestMapping(method = GET, value = "/{id}")
    @ResponseBody
    public User findAll(@PathVariable("id") int id) {
        return RestPreconditions.checkFound(userService.getById(id));
    }

    /**
     * Create a new user with an auto-generated id and email and name as passed
     * values.
     */
    @RequestMapping(method = POST, value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public long create(@RequestBody User user) {
        RestPreconditions.checkFound(user);
        return userService.create(user);
    }

    /**
     * Delete the user with the passed id.
     */
    @RequestMapping(method = DELETE, value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void delete(@PathVariable("id") long id) {
        User user = new User(id);
        userService.delete(user);
    }

//    /**
//     * Retrieve the id for the user with the passed email address.
//     */
//    @RequestMapping(method=GET, value = "/get-by-email")
//    @ResponseBody
//    public String getByEmail(String email) {
//        String userId;
//        try {
//            User user = userService.getByEmail(email);
//            userId = String.valueOf(user.getId());
//        } catch (Exception ex) {
//            return "User not found: " + ex.toString();
//        }
//        return "The user id is: " + userId;
//    }

    /**
     * Update the email and the name for the user indentified by the passed id.
     */
    @RequestMapping(method = PUT, value = "/update")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void updateName(@RequestBody User user) {
        RestPreconditions.checkFound(user);
        RestPreconditions.checkFound(userService.getById(user.getId()));
        userService.update(user);
    }
}