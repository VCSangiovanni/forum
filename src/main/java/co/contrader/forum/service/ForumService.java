package co.contrader.forum.service;

import co.contrader.forum.dto.*;
import co.contrader.forum.exception.BadCredentialException;
import co.contrader.forum.exception.GenericAlreadyExistException;
import co.contrader.forum.exception.GenericNotExistExceprion;
import co.contrader.forum.mapper.FCatMapper;
import co.contrader.forum.mapper.FPMapper;
import co.contrader.forum.mapper.FTMapper;
import co.contrader.forum.model.ForumCategory;
import co.contrader.forum.model.ForumPost;
import co.contrader.forum.model.ForumTopic;
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

    FTMapper ftMapper = FTMapper.INSTANCE;

    FPMapper fpMapper = FPMapper.INSTANCE;


    /**
     * Category Service
     */

    public ForumCategoryDTO insertCategory(InsCatDTO catDTO) {
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
                throw new BadCredentialException();
            }
        } else {
            throw new GenericAlreadyExistException();
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
           throw new GenericNotExistExceprion();
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
                    throw new GenericAlreadyExistException();
                }
            } else {
                throw new BadCredentialException();
            }
        } else {
            throw new GenericNotExistExceprion();
        }
    }

    public void deleteById(Long id){
        categoryRepo.deleteById(id);
    }

    public void deleteAll(){
        categoryRepo.deleteAll();
    }

    /**
     * Topic Service
     */

    public ForumTopicDTO insertTopic(InsTopicDTO newTopic, String categoryTitle){
        if(categoryRepo.findByCategoryTitle(categoryTitle) != null){
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (!user.getUserRole().equals(Role.GUEST)){
                var topicToInsert = ForumTopic.builder()
                        .topicTitle(newTopic.getTopicTitle())
                        .topicText(newTopic.getTopicText())
                        .topicCategory(categoryRepo.findByCategoryTitle(categoryTitle))
                        .updatedAt(System.currentTimeMillis())
                        .createdAt(System.currentTimeMillis())
                        .createdBy(user)
                        .build();
                topicRepo.save(topicToInsert);
                return ftMapper.toDto(topicToInsert);
            }else {
                throw new BadCredentialException();
            }
        }else {
            throw new GenericNotExistExceprion();
        }
    }

    /**
     * Post Service
     */

    public ForumPostDTO insertPost(InsPostDTO newPost, Long topicId){
        if (topicRepo.findById(topicId).isPresent()){
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (!user.getUserRole().equals(Role.GUEST)){
                var postToInsert = ForumPost.builder()
                        .postText(newPost.getPostText())
                        .updatedAt(System.currentTimeMillis())
                        .createdAt(System.currentTimeMillis())
                        .forumTopic(topicRepo.findById(topicId).get())
                        .createdBy(user)
                        .build();
                postRepo.save(postToInsert);
                return fpMapper.toDto(postToInsert);
            }else {
                throw new BadCredentialException();
            }
        }else {
            throw new GenericNotExistExceprion();
        }
    }

}
