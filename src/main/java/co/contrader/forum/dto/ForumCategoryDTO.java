package co.contrader.forum.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForumCategoryDTO {

    private Long id;
    private String categoryTitle;
    private Long createdAt;
    private Long updatedAt;
    private UserDTO createdBy;
}
