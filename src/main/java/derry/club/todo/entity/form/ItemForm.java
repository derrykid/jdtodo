package derry.club.todo.entity.form;

public class ItemForm {

    private long id;
    private String title;
    private boolean isCompleted;

    @Override
    public String toString() {
        return "ItemForm{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isCompleted=" + isCompleted +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
