package derry.club.todo.service;

import derry.club.todo.entity.User;
import derry.club.todo.entity.form.UserForm;
import derry.club.todo.exception.UserNotFoundException;
import derry.club.todo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User createUser(UserForm userForm) {
        // can create user with same name
        // cuz the id is unique
        User user = new User();
        user.setName(userForm.getName());
        return userRepository.save(user);
    }

    @Transactional
    public User updateUserById(long userId, UserForm userForm) {

        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException(userId));

        user.setName(userForm.getName());

        return userRepository.save(user);
    }


    public void deleteUserById(long userId) {
        userRepository.deleteById(userId);
    }
}
