package derry.club.todo.exception;

public class ItemNotFoundException extends RuntimeException{

    public ItemNotFoundException(long id) {
        super("Item of id: " + id + " was not found.");
    }
}
