package co.contrader.forum.controller;

import co.contrader.forum.dto.ForumCategoryDTO;
import co.contrader.forum.dto.InsCatDTO;
import co.contrader.forum.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/readAll")
    public List<ForumCategoryDTO> getAll(){
        return forumService.readAll();
    }

    @GetMapping("/readById")
    public ForumCategoryDTO readById(@RequestParam Long id) throws Exception {
        return forumService.readById(id);
    }

    @PutMapping("/updateCategory")
    public ForumCategoryDTO updateCategoryTitle(@RequestParam Long id, @RequestBody InsCatDTO catDTO) throws Exception {
        return forumService.updateCategoryTitle(id, catDTO);
    }

    @DeleteMapping("/deleteById")
    public void deleteCategoryById(@RequestParam Long id) {
        forumService.deleteById(id);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllCategory() {
        forumService.deleteAll();
    }

}
