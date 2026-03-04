package pt.luis.blogapp.api.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pt.luis.blogapp.api.models.entities.entity.BaseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "persons_stats")
public class PersonStats extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "persons_id", nullable = false)
    private Person person;

    private int categoriesCount;
    private int postsCount;
    private int commentsCount;
}
