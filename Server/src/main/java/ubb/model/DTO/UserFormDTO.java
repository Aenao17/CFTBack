package ubb.model.DTO;

import java.util.List;

public class UserFormDTO {
    private List<String> responses;

    public UserFormDTO() {
    }

    public UserFormDTO(List<String> responses) {
        this.responses = responses;
    }

    public List<String> getResponses() {
        return responses;
    }

    public void setResponses(List<String> responses) {
        this.responses = responses;
    }
}
