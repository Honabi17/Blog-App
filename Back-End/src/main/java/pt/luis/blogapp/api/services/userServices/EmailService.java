package pt.luis.blogapp.api.services.userServices;

public interface EmailService {

    void sendPasswordResetEmail(String to, String token);
}
