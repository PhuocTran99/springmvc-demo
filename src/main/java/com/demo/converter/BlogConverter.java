package com.demo.converter;

import com.demo.dto.BlogDTO;
import com.demo.entity.BlogEntity;
import com.demo.repository.IBlogRepository;
import com.demo.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BlogConverter {

    @Autowired
    private IBlogRepository blogRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    public BlogDTO toDto(BlogEntity blogEntity) {
        BlogDTO blogDTO = new BlogDTO();
        blogDTO.setId(blogEntity.getId());
        blogDTO.setTitle(blogEntity.getTitle());
        blogDTO.setThumbnail(blogEntity.getThumbnail());
        blogDTO.setShortDescription(blogEntity.getShortDescription());
        blogDTO.setContent(blogEntity.getContent());
        blogDTO.setCategoryId(blogEntity.getCategory().getId());
        return blogDTO;
    }

    public BlogEntity toEntity(BlogDTO blogDTO) {
        BlogEntity blogEntity = new BlogEntity();
        if (blogDTO.getId() != 0) {
            blogEntity.setId(blogDTO.getId());
            blogEntity.setCreatedDate(blogRepository.findOne(blogDTO.getId()).getCreatedDate());
            blogEntity.setCreatedBy(blogRepository.findOne(blogDTO.getId()).getCreatedBy());
        }
        blogEntity.setTitle(blogDTO.getTitle());
        blogEntity.setThumbnail(blogDTO.getThumbnail());
        blogEntity.setShortDescription(blogDTO.getShortDescription());
        blogEntity.setContent(blogDTO.getContent());
        blogEntity.setCategory(categoryRepository.findOne(blogDTO.getCategoryId()));
        return blogEntity;
    }
}
