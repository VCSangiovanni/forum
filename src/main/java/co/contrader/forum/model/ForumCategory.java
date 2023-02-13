package co.contrader.forum.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.Nullable;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForumCategory{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String categoryTitle;
    private Long createdAt;
    private Long updatedAt;

    @ManyToOne
    @Nullable
    private User createdBy;

}
