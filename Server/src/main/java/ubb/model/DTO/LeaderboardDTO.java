package ubb.model.DTO;

public class LeaderboardDTO {
    private String username;
    private Integer score;

    public LeaderboardDTO(String username, Integer score) {
        this.username = username;
        this.score = score;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
