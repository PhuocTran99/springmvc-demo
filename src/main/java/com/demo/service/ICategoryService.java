package com.demo.service;

import com.demo.dto.CategoryDTO;
import com.demo.entity.CategoryEntity;
import com.demo.model.CategoryModel;

import java.util.List;
import java.util.Map;

public interface ICategoryService {
    List<CategoryDTO> findAll();
    Map<Long, String> findAllAsMap();
    CategoryDTO findOne(long id);
}
