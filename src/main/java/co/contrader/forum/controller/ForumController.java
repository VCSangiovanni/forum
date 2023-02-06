package co.contrader.forum.controller;

import co.contrader.forum.dto.ForumCategoryDTO;
import co.contrader.forum.dto.InsCatDTO;
import co.contrader.forum.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/forum")
public class ForumController {

    @Autowired
    private ForumService forumService;

    /**
     * Category Controller
     */

    @PostMapping("/newCategory")
    public ForumCategoryDTO insertCategory(@RequestBody InsCatDTO catDTO) throws Exception {
        return forumService.insertCategory(catDTO);
    }

}
