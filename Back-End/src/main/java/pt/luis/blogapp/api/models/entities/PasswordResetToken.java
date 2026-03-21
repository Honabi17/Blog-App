package pt.luis.blogapp.api.models.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import pt.luis.blogapp.api.models.entities.entity.BaseEntity;

import java.time.LocalDateTime;


@Entity

public class PasswordResetToken extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String token;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    protected PasswordResetToken(){}

    public PasswordResetToken(
            String token,
            User user,
            LocalDateTime expiresAt
    ){
        this.token = token;
        this.user = user;
        this.expiresAt = expiresAt;
    }

    public String getToken(){
        return token;
    }

    public User getUser(){
        return user;
    }

    public LocalDateTime getExpiresAt(){
        return expiresAt;
    }

}
