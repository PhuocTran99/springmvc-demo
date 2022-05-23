package com.demo.dao.impl;

import com.demo.dao.GenericDAO;
import com.demo.mapper.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbstractDAO<T> implements GenericDAO<T> {

    public Connection getJDBC() {
        final String url = "jdbc:mysql://localhost:3306/database";
        final String user = "root";
        final String password = "12345";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
        List<T> result = new ArrayList<>();
        Connection conn = getJDBC();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            setParameter(pstmt, parameters);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                result.add(rowMapper.mapRow(rs));
            }
            return result; // tra ve List rong neu khong tim thay
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void update(String sql, Object... parameters) {
        Connection conn = getJDBC();
        try {
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            setParameter(pstmt, parameters);
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public long insert(String sql, Object... parameters) {
        Connection conn = getJDBC();
        long id = 0L;
        try {
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            setParameter(pstmt, parameters);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }
            conn.commit();
            return id;
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    private void setParameter(PreparedStatement pstmt, Object... parameters) throws SQLException {
        for (int i = 0; i < parameters.length; i++) {
            Object parameter = parameters[i];
            int index = i + 1;
            if (parameter instanceof Long) {
                pstmt.setLong(index, (Long) parameter);
            } else if (parameter instanceof Integer) {
                pstmt.setInt(index, (Integer) parameter);
            } else if (parameter instanceof String) {
                pstmt.setString(index, (String) parameter);
            } else if (parameter instanceof Timestamp) {
                pstmt.setTimestamp(index, (Timestamp) parameter);
            }
        }
    }
}
