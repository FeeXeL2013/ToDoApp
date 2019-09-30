package sample.model;


public class Task {

    private long dateCreation;

    private String description;

    private String task;

    public Task() {
    }

    public Task(long dateCreation, String description, String task) {
        this.dateCreation = dateCreation;
        this.description = description;
        this.task = task;
    }

    public long getDatecreated() {
        return dateCreation;
    }

    public void setDatecreated(long dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
