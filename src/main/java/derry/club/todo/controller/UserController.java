package derry.club.todo.controller;

import derry.club.todo.entity.User;
import derry.club.todo.entity.form.UserForm;
import derry.club.todo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    // TODO update to return with HTTP response

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{userId}")
    public User getUserById(@PathVariable long userId) {
        logger.info("Get user by id: {}", userId);
        return userService.getUserById(userId);
    }

    @PostMapping(path = "/create")
    public User createUser(@RequestBody UserForm userForm) {
        logger.info("Create user by name: {}", userForm.getName());
        User user =  userService.createUser(userForm);
        logger.info("User Id: {}, Name: {} created.", user.getId(), user.getName());
        return user;
    }

    @PatchMapping(path = "/{userId}")
    public User updateUserById(@PathVariable long userId, @RequestBody UserForm userForm) {
        logger.info("Update user of id: {}", userId);
        User user = userService.updateUserById(userId, userForm);
        logger.info("User updated. Id: {}, Name: {}", user.getId(), user.getName());
        return user;
    }

    @DeleteMapping(value = "/{userId}")
    public void deleteUserById(@PathVariable long userId) {
        logger.info("Delete user by id: {}", userId);
    }


}
