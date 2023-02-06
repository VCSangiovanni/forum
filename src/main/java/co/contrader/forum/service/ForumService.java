package co.contrader.forum.service;

import co.contrader.forum.dto.ForumCategoryDTO;
import co.contrader.forum.dto.InsCatDTO;
import co.contrader.forum.mapper.FCatMapper;
import co.contrader.forum.model.ForumCategory;
import co.contrader.forum.model.User;
import co.contrader.forum.repository.ForumCategoryRepository;
import co.contrader.forum.repository.ForumPostRepository;
import co.contrader.forum.repository.ForumTopicRepository;
import co.contrader.forum.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ForumService {

    @Autowired
    private ForumCategoryRepository categoryRepo;
    @Autowired
    private ForumTopicRepository topicRepo;
    @Autowired
    private ForumPostRepository postRepo;

    FCatMapper fCatMapper = FCatMapper.INSTANCE;


    /**
     * Category Service
     */

    public ForumCategoryDTO insertCategory(InsCatDTO catDTO) throws Exception {
        if (categoryRepo.findByCategoryTitle(catDTO.getCategoryTitle()) == null) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (user.getUserRole().equals(Role.ADMIN) || user.getUserRole().equals(Role.FOUNDER)) {
                var catToInsert = ForumCategory.builder()
                        .categoryTitle(catDTO.getCategoryTitle())
                        .createdAt(System.currentTimeMillis())
                        .updatedAt(System.currentTimeMillis())
                        .createdBy(user)
                        .build();
                categoryRepo.save(catToInsert);
                return fCatMapper.toDto(catToInsert);
            } else {
                throw new Exception("Only ADMIN or FOUNDER can create Category");
            }
        } else {
            throw new Exception("Category Already Exist");
        }
    }

    public List<ForumCategoryDTO> readAll(){
        return fCatMapper.toDtoList(categoryRepo.findAll());
    }

    public ForumCategoryDTO readById(Long id) throws Exception {
       Optional<ForumCategory> cat = categoryRepo.findById(id);
       if (cat.isPresent()) {
           return fCatMapper.toDto(cat.get());
       }else {
           throw new Exception("Category not Exist");
       }
    }

    public ForumCategoryDTO updateCategoryTitle(Long id, InsCatDTO catDTO) throws Exception {
        Optional<ForumCategory> catToUpdate = categoryRepo.findById(id);
        if (catToUpdate.isPresent()) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (user.getUserRole().equals(Role.FOUNDER) || user.getUserRole().equals(Role.ADMIN)) {
                if (categoryRepo.findByCategoryTitle(catDTO.getCategoryTitle()) == null) {
                    catToUpdate.get().setCategoryTitle(catDTO.getCategoryTitle());
                    catToUpdate.get().setUpdatedAt(System.currentTimeMillis());
                    return fCatMapper.toDto(catToUpdate.get());
                } else {
                    throw new Exception("Category Already Exist");
                }
            } else {
                throw new Exception("Only ADMIN or FOUNDER can create Category");
            }
        } else {
            throw new Exception("Category not Exist");
        }
    }

    public void deleteById(Long id){
        categoryRepo.deleteById(id);
    }

    public void deleteAll(){
        categoryRepo.deleteAll();
    }

}
