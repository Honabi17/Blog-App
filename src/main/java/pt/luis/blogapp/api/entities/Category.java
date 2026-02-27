package pt.luis.blogapp.api.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pt.luis.blogapp.api.entities.entity.BaseEntity;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
public class Category extends BaseEntity {


    @Column(nullable = false, unique = true)
    @Size(max = 20)
    private String name;

    @Column(nullable = false)
    @Size(max = 50)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @OneToMany(mappedBy = "category")
    private Set<Post> posts = new HashSet<>();
}
