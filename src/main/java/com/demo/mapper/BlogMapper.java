package com.demo.mapper;

import com.demo.model.BlogModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BlogMapper implements RowMapper<BlogModel>{

    @Override
    public BlogModel mapRow(ResultSet rs) {
        try {
            BlogModel blogModel = new BlogModel();
            blogModel.setId(rs.getLong("id"));
            blogModel.setTitle(rs.getString("title"));
            blogModel.setThumbnail(rs.getString("thumbnail"));
            blogModel.setShortDescription(rs.getString("shortdescription"));
            blogModel.setContent(rs.getString("content"));
            blogModel.setCategoryId(rs.getLong("categoryid"));
            blogModel.setCreatedDate(rs.getTimestamp("createddate"));
            blogModel.setModifiedDate(rs.getTimestamp("modifieddate"));
            blogModel.setCreatedBy(rs.getString("createdby"));
            blogModel.setModifiedBy(rs.getString("modifiedby"));
            return blogModel;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
