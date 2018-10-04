package arnaudg.web.controllers;

import arnaudg.persistence.models.User;
import arnaudg.persistence.service.UserService;

import arnaudg.web.util.RestPreconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.ws.rs.core.Response;
import static com.google.common.net.HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Class UserController
 */
@Controller
@RequestMapping(value = "/user")
//@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Get all the users.
     */
    @RequestMapping(method = GET)
    @ResponseBody
    public Response findAll() {
        return Response.status(Response.Status.OK)
                .entity(userService.findAll())
                .header(ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                .build();
    }

    /**
     * Get one user by its id.
     */
    @RequestMapping(method = GET, value = "/{id}")
    @ResponseBody
    public Response findAll(@PathVariable("id") int id) {
        return Response.status(Response.Status.OK)
                .entity(RestPreconditions.checkFound(userService.getById(id)))
                .header(ACCESS_CONTROL_ALLOW_ORIGIN, "*")
                .build();
    }

    /**
     * Create a new user with an auto-generated id and email and name as passed
     * values.
     */
    @RequestMapping(method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Response create(@RequestBody User user) {
        RestPreconditions.checkFound(user);
        return Response.status(Response.Status.CREATED).entity(userService.create(user)).build();
    }

    /**
     * Delete the user with the passed id.
     */
    @RequestMapping(method = DELETE, value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response delete(@PathVariable("id") long id) {
        User user = new User(id);
        userService.delete(user);

        return Response.status(Response.Status.OK).build();
    }

    /**
     * Retrieve the id for the user with the passed email address.
     */
    @RequestMapping(method=GET, value = "/email/{email}")
    @ResponseBody
    public Response getByEmail(@PathVariable(value = "email") String email) {
        String userId = null;
        try {
            User user = userService.getByEmail(email);
            userId = String.valueOf(user.getId());
        } catch (Exception ex) {
            System.out.println("User not found: " + ex.toString());
        }
        return Response.status(Response.Status.OK).entity(userId).build();
    }

    /**
     * Update the email and the name for the user indentified by the passed id.
     */
    @RequestMapping(method = PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response updateName(@RequestBody User user) {
        RestPreconditions.checkFound(user);
        RestPreconditions.checkFound(userService.getById(user.getId()));
        userService.update(user);

        return Response.status(Response.Status.OK).build();
    }
}