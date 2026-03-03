package pt.luis.blogapp.api.services;

public interface EmailService {

    void sendPasswordResetEmail(String to, String token);
}
