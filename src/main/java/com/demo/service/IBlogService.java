package com.demo.service;

import com.demo.dto.BlogDTO;
import com.demo.model.BlogModel;

import java.util.List;

public interface IBlogService {
    List<BlogDTO> findAll();
    BlogDTO findOne(long id);
    BlogDTO save(BlogDTO blogDTO);
    void delete(long[] ids);
//    BlogModel findOne(long id);
//    List<BlogModel> findByCategoryId(long categoryId);
//    BlogModel create(BlogModel blogModel);
//    BlogModel modify(BlogModel blogModel);
//    List<BlogModel> delete(long[] ids);
}
