package com.demo.dao;

import com.demo.mapper.RowMapper;

import java.util.List;

public interface GenericDAO<T> {
    List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters);
    void update(String sql, Object... parameters);
    long insert(String sql, Object... parameters);
}
