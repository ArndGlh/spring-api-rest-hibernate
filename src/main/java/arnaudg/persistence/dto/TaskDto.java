package arnaudg.persistence.dto;

import javax.validation.constraints.NotNull;

public class TaskDto {

    @NotNull
    private int id;

    @NotNull
    private int progressId;

    @NotNull
    private int actualProgress;

    @NotNull
    private int maxProgress;

    private String name;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProgressId() {
        return progressId;
    }

    public void setProgressId(int progressId) {
        this.progressId = progressId;
    }

    public int getActualProgress() {
        return actualProgress;
    }

    public void setActualProgress(int actualProgress) {
        this.actualProgress = actualProgress;
    }

    public int getMaxProgress() {
        return maxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
