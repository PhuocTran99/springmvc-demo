package com.demo.mapper;

import com.demo.model.CategoryModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements RowMapper<CategoryModel> {

    @Override
    public CategoryModel mapRow(ResultSet rs) {
        try {
            CategoryModel categoryModel = new CategoryModel();
            categoryModel.setId(rs.getLong("id"));
            categoryModel.setName(rs.getString("name"));
            categoryModel.setCode(rs.getString("code"));
            categoryModel.setCreatedDate(rs.getTimestamp("createddate"));
            categoryModel.setModifiedDate(rs.getTimestamp("modifieddate"));
            categoryModel.setCreatedBy(rs.getString("createdby"));
            categoryModel.setModifiedBy(rs.getString("modifiedby"));
            return categoryModel;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
