package ubb.model;

public class Response {
    private String response;
    private Integer score;

    public Response(String response, Integer score) {
        this.response = response;
        this.score = score;
    }

    public Response() {
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
