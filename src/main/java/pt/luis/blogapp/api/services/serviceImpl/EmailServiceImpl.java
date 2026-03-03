package pt.luis.blogapp.api.services.serviceImpl;

import org.springframework.stereotype.Service;
import pt.luis.blogapp.api.services.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    @Override
    public void sendPasswordResetEmail(String to, String token) {
        System.out.println("Send email to: " + to);
        System.out.println("Token: " + token);
    }
}
