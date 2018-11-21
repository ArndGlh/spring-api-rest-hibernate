package arnaudg.persistence.dto;

import javax.validation.constraints.NotNull;

public class ProgressDto {

    private int id;

    @NotNull
    private int taskId;

    @NotNull
    private int userId;

    @NotNull
    private int actual_progress;

    @NotNull
    private int max_progress;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getActual_progress() {
        return actual_progress;
    }

    public void setActual_progress(int actual_progress) {
        this.actual_progress = actual_progress;
    }

    public int getMax_progress() {
        return max_progress;
    }

    public void setMax_progress(int max_progress) {
        this.max_progress = max_progress;
    }
}
