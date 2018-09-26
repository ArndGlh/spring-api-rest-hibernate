package arnaudg.persistence.dto;

import javax.validation.constraints.NotNull;

public class ProgressDto {

    private int id;

    @NotNull
    private int gameId;

    @NotNull
    private int userId;

    @NotNull
    private double completion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getCompletion() {
        return completion;
    }

    public void setCompletion(double completion) {
        this.completion = completion;
    }
}
