package co.contrader.forum.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForumTopicDTO {

    private Long id;
    private String topicTitle;
    private String topicText;
    public Long createdAt;
    public Long updatedAt;
    private ForumCategoryDTO topicCategory;
    private UserDTO createdBy;

}
