package ubb.model;

import java.util.List;

public class Question {
    private String question;
    private List<Response> responses;

    public Question(String question, List<Response> responses) {
        this.question = question;
        this.responses = responses;
    }

    public Question() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Response> getResponses() {
        return responses;
    }

    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }
}
