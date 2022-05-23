package com.demo.dao.impl;

import com.demo.dao.ICategoryDAO;
import com.demo.mapper.CategoryMapper;
import com.demo.model.CategoryModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO {

    @Override
    public List<CategoryModel> findAll() {
        String sql = "SELECT * FROM category";
        return query(sql, new CategoryMapper());
    }

    @Override
    public CategoryModel findOne(long id) {
        String sql = "SELECT * FROM category WHERE id = ?";
        List<CategoryModel> list = query(sql, new CategoryMapper(), id);
        return list.isEmpty()?null:list.get(0);
    }
}
