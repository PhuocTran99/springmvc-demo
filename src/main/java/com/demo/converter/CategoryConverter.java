package com.demo.converter;

import com.demo.dto.CategoryDTO;
import com.demo.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

    public CategoryDTO toDto(CategoryEntity categoryEntity) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(categoryEntity.getId());
        categoryDTO.setName(categoryEntity.getName());
        categoryDTO.setCode(categoryEntity.getCode());
        return categoryDTO;
    }

    public CategoryEntity toEntity(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(categoryDTO.getName());
        categoryEntity.setCode(categoryDTO.getCode());
        return categoryEntity;
    }
}
