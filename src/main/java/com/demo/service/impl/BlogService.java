package com.demo.service.impl;

import com.demo.converter.BlogConverter;
import com.demo.dto.BlogDTO;
import com.demo.entity.BlogEntity;
import com.demo.repository.IBlogRepository;
import com.demo.repository.ICategoryRepository;
import com.demo.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService implements IBlogService {

    @Autowired
    private IBlogRepository blogRepository;

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private BlogConverter blogConverter;

    @Override
    public List<BlogDTO> findAll() {
        List<BlogEntity> entities = blogRepository.findAll();
        List<BlogDTO> dtos = new ArrayList<>();
        for (BlogEntity item : entities) {
            dtos.add(blogConverter.toDto(item));
        }
        return dtos;
    }

    @Override
    public BlogDTO findOne(long id) {
        BlogEntity blogEntity = blogRepository.findOne(id);
        return blogConverter.toDto(blogEntity);
    }

    @Override
    public BlogDTO save(BlogDTO blogDTO) {
        return blogConverter.toDto(blogRepository.save(blogConverter.toEntity(blogDTO)));
    }

    @Override
    public void delete(long[] ids) {
        for (long id : ids) {
            blogRepository.delete(id);
        }
    }

//    @Override
//    public BlogModel findOne(long id) {
//        return blogDAO.findOne(id);
//    }
//
//    @Override
//    public List<BlogModel> findByCategoryId(long categoryId) {
//        return blogDAO.findByCategoryId(categoryId);
//    }
//
//    @Override
//    public BlogModel create(BlogModel blogModel) {
//        blogModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
//        long id = blogDAO.create(blogModel);
//        return blogDAO.findOne(id);
//    }
//
//    @Override
//    public BlogModel modify(BlogModel blogModel) {
//        blogModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
//        blogDAO.modify(blogModel);
//        return blogDAO.findOne(blogModel.getId());
//    }

//    @Override
//    public List<BlogModel> delete(long[] ids) {
//        for (long id : ids) {
//            commentDAO.deleteByBlogId(id);
//            blogDAO.delete(id);
//        }
//        return blogDAO.findAll();
//    }
}
