package derry.club.todo.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(long id) {
        super("User of id: " + id + " was not found.");
    }
}
