package co.contrader.forum.dto;

import co.contrader.forum.model.ForumTopic;
import co.contrader.forum.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForumPostDTO {

    private Long id;
    private String postText;
    private Long createdAt;
    private Long updatedAt;
    private ForumTopicDTO forumTopic;
    private UserDTO createdBy;

}
