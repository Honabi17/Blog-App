package pt.luis.blogapp.api.infrastructure.security;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordHasher {

    public static String hash(String rawPassword){

        return BCrypt.hashpw(rawPassword, BCrypt.gensalt());
    }

    public static boolean matches(String rawPassword, String hash){

        return BCrypt.checkpw(rawPassword, hash);
    }
}
