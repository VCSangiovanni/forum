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
public class ForumPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String postText;
    private Long createdAt;
    private Long updatedAt;
    @ManyToOne
    @Nullable
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ForumTopic forumTopic;
    @ManyToOne
    @Nullable
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private User createdBy;
}
