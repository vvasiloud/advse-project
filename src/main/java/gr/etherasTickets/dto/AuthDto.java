package gr.etherasTickets.dto;


public class AuthDto {
    private String userId;

    public AuthDto(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
