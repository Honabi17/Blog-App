package pt.luis.blogapp.api.infrastructure.security;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;



@Embeddable
public class Password {

    @Column(name = "password_hash", nullable = false)
    private String hash;

    protected Password(){}

    private Password(String hash){
        this.hash = hash;
    }

    public static Password fromPlainText(String rawPassword){
        validate(rawPassword);

        String hashed = PasswordHasher.hash(rawPassword);
        return new Password(hashed);
    }

    public static Password fromHash(String hash){
        validate(hash);
        return new Password(hash);
    }

    public boolean matches(String rawPassword){
        return PasswordHasher.matches(rawPassword, this.hash);
    }

    private static void validate(String rawPassword){
        if(rawPassword == null || rawPassword.length() < 4){
            throw  new IllegalArgumentException("The password must be at least 4 characters long!");
        }
    }

    private static void validateHash(String hash){
        if(hash == null || hash.isBlank()){
            throw new IllegalArgumentException("Invalid hash!");
        }
    }

    public String getHash(){
        return hash;
    }
}
