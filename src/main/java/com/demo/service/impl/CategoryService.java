package com.demo.service.impl;

import com.demo.converter.CategoryConverter;
import com.demo.dto.CategoryDTO;
import com.demo.entity.CategoryEntity;
import com.demo.repository.ICategoryRepository;
import com.demo.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private CategoryConverter categoryConverter;

    @Override
    public List<CategoryDTO> findAll() {
        List<CategoryEntity> entities = categoryRepository.findAll();
        List<CategoryDTO> dtos = new ArrayList<>();
        for (CategoryEntity item : entities) {
            dtos.add(categoryConverter.toDto(item));
        }
        return dtos;
    }

    @Override
    public Map<Long, String> findAllAsMap() {
        Map<Long, String> result = new HashMap<>();
        List<CategoryEntity> entities = categoryRepository.findAll();
        for (CategoryEntity item : entities) {
            result.put(item.getId(), item.getName());
        }
        return result;
    }

    @Override
    public CategoryDTO findOne(long id) {
        CategoryEntity categoryEntity = categoryRepository.findOne(id);
        return categoryConverter.toDto(categoryEntity);
    }
}
