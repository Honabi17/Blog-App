package pt.luis.blogapp.api.infrastructure.securities.password;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


@Embeddable
public class Password {

    @Column(name = "password_hash", nullable = false)
    private String hash;

    protected Password(){}

    public Password(String hash){
        this.hash = hash;
    }

    public String getHash(){
        return hash;
    }
}
